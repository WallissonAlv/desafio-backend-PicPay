package br.com.wallissonalves.picpay.service;

import org.springframework.stereotype.Service;

import br.com.wallissonalves.picpay.client.AuthorizationClient;
import br.com.wallissonalves.picpay.controller.dto.TransferDto;
import br.com.wallissonalves.picpay.exception.PicPayException;

@Service
public class AuthorizationService {

	private final AuthorizationClient authorizationClient;
	public AuthorizationService (AuthorizationClient authorizationClient) {
		this.authorizationClient = authorizationClient;
	}
	
	public boolean isAuthorized (TransferDto transferDto) {
		var resp = authorizationClient.isAuthorized();
		
		if(resp.getStatusCode().isError()) {
			throw new PicPayException();
		}
		
		return resp.getBody().authorized();
	}
}
