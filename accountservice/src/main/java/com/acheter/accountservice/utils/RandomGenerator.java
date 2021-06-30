package com.acheter.accountservice.utils;

import java.security.SecureRandom;

public class RandomGenerator {
	private final static String NUMBER_SEQUENCE = "8901567234";
	private final static String ALPHA_NUMERIC_WITH_SPECIAL_SEQUENCE = "23456abcdefghistuvwxyz01789-$jklmnopqr";

	public static String generateOtp(int length) {
		SecureRandom secureRandom = null;
		char[] otpSequence = null;
		String otp = null;

		secureRandom = new SecureRandom();
		otpSequence = new char[length];
		for (int i = 0; i < length; i++) {
			otpSequence[i] = NUMBER_SEQUENCE.charAt(secureRandom.nextInt(10));
		}

		otp = new String(otpSequence);
		return otp;
	}

	public static String generateVerificationCode(int length) {
		SecureRandom secureRandom = null;
		char[] verificationCodeSequence = null;
		String verificationCode = null;

		secureRandom = new SecureRandom();
		verificationCodeSequence = new char[length];
		for (int i = 0; i < length; i++) {
			verificationCodeSequence[i] = ALPHA_NUMERIC_WITH_SPECIAL_SEQUENCE.charAt(secureRandom.nextInt(38));
		}

		verificationCode = new String(verificationCodeSequence);
		return verificationCode;

	}

}
