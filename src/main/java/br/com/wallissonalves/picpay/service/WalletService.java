package br.com.wallissonalves.picpay.service;

import org.springframework.stereotype.Service;

import br.com.wallissonalves.picpay.controller.dto.CreateWalletDto;
import br.com.wallissonalves.picpay.entity.Wallet;
import br.com.wallissonalves.picpay.exception.WalletDataAlreadyExists;
import br.com.wallissonalves.picpay.repository.WalletRepository;

@Service
public class WalletService {

	private final WalletRepository walletRepository;
	public WalletService(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}
	
	public Wallet createWallet(CreateWalletDto dto) {
		var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(),dto.email());
		if(walletDb.isPresent()) {
			throw new WalletDataAlreadyExists("CPF/CNPJ or EMAIL already exists");
		}
		return walletRepository.save(dto.toWallet());
	}

}
