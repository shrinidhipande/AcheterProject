package com.acheter.accountservice.dto.account;

import lombok.Data;

@Data
public class ResetPasswordDto {
	private long userAccountNo;
	private String password;

}
