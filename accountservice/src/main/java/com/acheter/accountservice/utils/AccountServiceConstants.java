package com.acheter.accountservice.utils;

public interface AccountServiceConstants {
	String ROLE_CUSTOMER = "customer";
	
	String STATUS_USER_NEW = "N";
	String STATUS_USER_ACTIVE = "A";
	

	String SYSTEM_USER = "system";

	String ERR_CODE_USER_NOT_FOUND = "201";
	String ERR_CODE_USER_NOT_FOUND_KEY = "userAccount.notFound";
	String ERR_CODE_EMAIL_VERIFICATION_FAILED = "202";
	String ERR_CODE_EMAIL_VERIFICATION_FAILED_KEY = "emailVerificationCode.failed";
	
	String ERR_CODE_OTP_CODE_FAILED = "203";
	String ERR_CODE_OTP_CODE_FAILED_KEY = "otpCode.failed";
}
