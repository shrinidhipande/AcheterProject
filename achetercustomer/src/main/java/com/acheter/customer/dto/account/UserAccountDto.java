package com.acheter.customer.dto.account;

import lombok.Data;

@Data
public class UserAccountDto {
	private int userAccountNo;
	private String firstName;
	private String lastName;
	private String displayName;
	private String emailAddress;
	private String mobileNo;
	private String password;
	private String roleName;
	private String status;
	protected int emailVerificationStatus;
	protected int otpCodeStatus;

}
