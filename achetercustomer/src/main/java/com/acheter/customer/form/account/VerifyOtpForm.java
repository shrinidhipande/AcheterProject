package com.acheter.customer.form.account;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class VerifyOtpForm {
	private long userAccountNo;
	@NotBlank
	private String otp;
	
}