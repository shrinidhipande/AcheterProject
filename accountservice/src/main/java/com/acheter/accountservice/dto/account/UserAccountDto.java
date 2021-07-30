package com.acheter.accountservice.dto.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserAccountDto {
	protected long userAccountNo;
	protected String firstName;
	protected String lastName;
	protected String displayName;
	protected String emailAddress;
	protected String mobileNo;
	protected String password;
	protected String roleName;
	protected String status;

}
