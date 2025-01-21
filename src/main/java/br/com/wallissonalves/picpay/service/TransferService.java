package br.com.wallissonalves.picpay.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import br.com.wallissonalves.picpay.controller.dto.TransferDto;
import br.com.wallissonalves.picpay.entity.Transfer;
import br.com.wallissonalves.picpay.entity.Wallet;
import br.com.wallissonalves.picpay.exception.InsufficientBalanceException;
import br.com.wallissonalves.picpay.exception.NotAuthorizedException;
import br.com.wallissonalves.picpay.exception.TransferNotAllowedForWalletTypeException;
import br.com.wallissonalves.picpay.exception.WalletNotFoundException;
import br.com.wallissonalves.picpay.repository.TransferRepository;
import br.com.wallissonalves.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;

@Service
public class TransferService {

	private final TransferRepository transferRepository;
	private final NotificationService notificationService;
	private final AuthorizationService authorizationService;
	private final WalletRepository walletRepository;
	public TransferService(
			TransferRepository transferRepository, 
			NotificationService notificationService,
			AuthorizationService authorizationService,
			WalletRepository walletRepository) {
		this.transferRepository = transferRepository;
		this.notificationService = notificationService;
		this.authorizationService = authorizationService;
		this.walletRepository = walletRepository;
	}

	@Transactional
	public Transfer transfer(TransferDto transferDto) {
		var sender = walletRepository.findById(transferDto.payer())
				.orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));
		
		var receiver = walletRepository.findById(transferDto.payee())
				.orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));
		
		validateTransfer(transferDto, sender);
		
		sender.debit(transferDto.value());
		receiver.credit(transferDto.value());
		
		var transfer = new Transfer(sender,receiver,transferDto.value());
		walletRepository.save(sender);
		walletRepository.save(receiver);
		var completedTransfer = transferRepository.save(transfer);
		
		CompletableFuture.runAsync(() -> notificationService.sendNotification(completedTransfer));
		
		return completedTransfer;
	}

	private void validateTransfer(TransferDto transferDto, Wallet sender) {

		if(!sender.isTransferAllowedForWalletType()) {
			throw new TransferNotAllowedForWalletTypeException();
		}
		if(!sender.isBalancerEqualOrGreaterThan(transferDto.value())) {
			throw new InsufficientBalanceException();
		}
		if(!authorizationService.isAuthorized(transferDto)) {
			throw new NotAuthorizedException();
		}
	}
}
