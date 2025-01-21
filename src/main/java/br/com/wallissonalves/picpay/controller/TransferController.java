package br.com.wallissonalves.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wallissonalves.picpay.controller.dto.TransferDto;
import br.com.wallissonalves.picpay.entity.Transfer;
import br.com.wallissonalves.picpay.service.TransferService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class TransferController {

	private final TransferService transferService;
	public TransferController(TransferService trasnferService) {
		this.transferService = trasnferService;
	}
	
	
	@PostMapping
	public ResponseEntity<Transfer> transfer (@RequestBody @Valid TransferDto dto){
		
		var body = transferService.transfer(dto);
		
		return ResponseEntity.ok(body);
	}
}
