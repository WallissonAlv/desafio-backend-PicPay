package br.com.wallissonalves.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class NotAuthorizedException extends PicPayException {
	private static final long serialVersionUID = 1L;

	@Override
	public ProblemDetail toProblemDetail() {
		var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		
		pb.setTitle("Transfer not Authorized.");
		pb.setDetail("Authorization service not authorized for this transfer");
		
		return pb;
	}
}
