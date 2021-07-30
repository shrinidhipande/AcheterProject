package com.acheter.accountservice.dto.account;

import lombok.Data;

@Data
public class ForgotPasswordDto {
	private String emailAddress;
	private String mobileNo;
}
