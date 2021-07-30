package com.acheter.accountservice.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountServiceException extends Throwable {
	private static final long serialVersionUID = -236952176150508274L;
	protected List<AcheterError> errors;

	public AccountServiceException(String message, AcheterError error) {
		super(message);
		errors = Arrays.asList(new AcheterError[] { error });
	}

	public AccountServiceException(List<AcheterError> errors) {
		super();
		this.errors = errors;
	}

	public AccountServiceException(String message, Throwable cause, List<AcheterError> errors) {
		super(message, cause);
		this.errors = errors;
	}

	public AccountServiceException(String message, List<AcheterError> errors) {
		super(message);
		this.errors = errors;
	}

	public AccountServiceException(Throwable cause, List<AcheterError> errors) {
		super(cause);
		this.errors = errors;
	}

	public List<AcheterError> getErrors() {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		return errors;
	}
}
