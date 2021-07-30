package com.acheter.accountservice.exception;

public class VerificationCodeMismatchException extends AccountServiceException {
	private static final long serialVersionUID = -5951381642241679617L;

	public VerificationCodeMismatchException(String message, AcheterError error) {
		super(message, error);
	}

}
