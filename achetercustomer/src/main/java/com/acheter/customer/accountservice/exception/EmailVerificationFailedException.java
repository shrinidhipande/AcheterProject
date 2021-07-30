package com.acheter.customer.accountservice.exception;

public class EmailVerificationFailedException extends AccountServiceException {

	private static final long serialVersionUID = 1197515501558979427L;

	public EmailVerificationFailedException() {
		super();
	}

	public EmailVerificationFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmailVerificationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailVerificationFailedException(String message) {
		super(message);
	}

	public EmailVerificationFailedException(Throwable cause) {
		super(cause);
	}

}
