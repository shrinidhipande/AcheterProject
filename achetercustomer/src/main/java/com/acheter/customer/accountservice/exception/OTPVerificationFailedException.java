package com.acheter.customer.accountservice.exception;

public class OTPVerificationFailedException extends AccountServiceException {

	private static final long serialVersionUID = 1727289559206942975L;

	public OTPVerificationFailedException() {
		super();
	}

	public OTPVerificationFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OTPVerificationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public OTPVerificationFailedException(String message) {
		super(message);
	}

	public OTPVerificationFailedException(Throwable cause) {
		super(cause);
	}

}
