package com.acheter.accountservice.exception;

import java.util.List;

public class UserAccountNotFoundException extends AccountServiceException {
	private static final long serialVersionUID = -4428259465163766933L;

	public UserAccountNotFoundException(String message, List<AcheterError> errors) {
		super(message, errors);
	}

	public UserAccountNotFoundException(String message, AcheterError error) {
		super(message, error);
	}
}
