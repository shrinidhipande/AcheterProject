package com.acheter.accountservice.dto.account;

import lombok.Data;

@Data
public class AccountVerificationStatusDto {
	protected long userAccountNo;
	protected int emailVerificationStatus;
	protected int otpCodeStatus;
}
