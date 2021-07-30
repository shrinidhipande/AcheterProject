package com.acheter.customer.accountservice.exception.decoder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.acheter.customer.accountservice.exception.AccountServiceError;
import com.acheter.customer.accountservice.exception.EmailVerificationFailedException;
import com.acheter.customer.accountservice.exception.OTPVerificationFailedException;
import com.acheter.customer.accountservice.exception.UserNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class AccountServiceExceptionDecoder implements ErrorDecoder {
	@Override
	public Exception decode(String methodKey, Response response) {
		Reader reader = null;
		ObjectMapper mapper = null;
		List<AccountServiceError> errors = null;
		UserNotFoundException userNotFoundException = null;
		OTPVerificationFailedException otpVerificationFailedException = null;
		EmailVerificationFailedException emailVerificationFailedException = null;
		try {

			if (response.status() == HttpStatus.NOT_FOUND.value()) {
				mapper = new ObjectMapper();
				reader = response.body().asReader(Charset.forName("utf-8"));
				errors = mapper.readValue(reader, new TypeReference<List<AccountServiceError>>() {
				});
				if (errors != null && errors.size() == 1) {
					if (errors.get(0).getErrorCode().equals("201")) {
						userNotFoundException = new UserNotFoundException();
						userNotFoundException.setErrors(errors);
					}
				}

			} else if (response.status() == HttpStatus.BAD_REQUEST.value()) {
				reader = response.body().asReader(Charset.forName("utf-8"));
				mapper = new ObjectMapper();
				errors = mapper.readValue(reader, new TypeReference<List<AccountServiceError>>() {
				});
				if (errors != null && errors.size() == 1) {
					if (errors.get(0).getErrorCode().equals("203")) {
						otpVerificationFailedException = new OTPVerificationFailedException();
						otpVerificationFailedException.setErrors(errors);
						return otpVerificationFailedException;
					} else if (errors.get(0).getErrorCode().equals("202")) {
						emailVerificationFailedException = new EmailVerificationFailedException();
						emailVerificationFailedException.setErrors(errors);
						return emailVerificationFailedException;
					}
				}
			}
		} catch (

		IOException e) {
			e.printStackTrace();
		}
		return userNotFoundException;
	}
}