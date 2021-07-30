package com.acheter.customer.accountservice.exception;

import java.util.List;

import lombok.Data;

@Data
public class AccountServiceException extends RuntimeException {
	private static final long serialVersionUID = 3501754149309803807L;
	private List<AccountServiceError> errors;

	public AccountServiceException() {
		super();
	}

	public AccountServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountServiceException(String message) {
		super(message);
	}

	public AccountServiceException(Throwable cause) {
		super(cause);
	}

}
