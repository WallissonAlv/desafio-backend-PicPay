package br.com.wallissonalves.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends PicPayException {
	private static final long serialVersionUID = 1L;

	private Long walletId;
	public WalletNotFoundException(Long walletId) {
		this.walletId = walletId;
	}
	
	public ProblemDetail toProblemDetail() {
		var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		pb.setTitle("Wallet Not Found.");
		pb.setDetail("There is no wallet with this id "+ walletId + " .");
		return pb;
	}

	
}
