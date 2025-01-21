package br.com.wallissonalves.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataAlreadyExists extends PicPayException {
	private static final long serialVersionUID = 1L;
	
	private String detail;
	public WalletDataAlreadyExists(String detail) {
		this.detail = detail;
	}

	@Override
	public ProblemDetail toProblemDetail() {
		var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		pb.setTitle("Wallet data Already Exists");
		pb.setDetail(detail);
		return pb;
	}

	
}
