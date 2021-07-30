package com.acheter.accountservice.exception;

import java.util.List;

public class EmailNotificationFailedException extends AccountServiceException {
	private static final long serialVersionUID = -213047980813990813L;

	public EmailNotificationFailedException(List<AcheterError> errors) {
		super(errors);
	}

	public EmailNotificationFailedException(String message, AcheterError error) {
		super(message, error);
	}

	public EmailNotificationFailedException(String message, List<AcheterError> errors) {
		super(message, errors);
	}

	public EmailNotificationFailedException(String message, Throwable cause, List<AcheterError> errors) {
		super(message, cause, errors);
	}

	public EmailNotificationFailedException(Throwable cause, List<AcheterError> errors) {
		super(cause, errors);
	}

}
