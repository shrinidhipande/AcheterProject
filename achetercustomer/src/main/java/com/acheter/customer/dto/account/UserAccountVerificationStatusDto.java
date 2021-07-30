package com.acheter.customer.dto.account;

import lombok.Data;

@Data
public class UserAccountVerificationStatusDto {
	protected long userAccountNo;
	protected int emailVerificationStatus;
	protected int otpCodeStatus;
}
