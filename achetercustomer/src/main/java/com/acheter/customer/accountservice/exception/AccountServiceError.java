package com.acheter.customer.accountservice.exception;

import lombok.Data;

@Data
public class AccountServiceError {
	protected String errorCode;
	protected String description;

}
