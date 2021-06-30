package com.acheter.customer.form.account;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ResetPasswordForm {
	private long userAccountNo;
	@NotBlank
	private String password;
	@NotBlank
	private String reTypePassword;

}