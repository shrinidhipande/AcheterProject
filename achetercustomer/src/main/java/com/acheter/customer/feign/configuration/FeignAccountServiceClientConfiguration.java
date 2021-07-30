package com.acheter.customer.feign.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.acheter.customer.accountservice.exception.decoder.AccountServiceExceptionDecoder;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignAccountServiceClientConfiguration {

	@Bean
	public AccountServiceExceptionDecoder accountServiceExceptionDecoder() {
		return new AccountServiceExceptionDecoder();
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthenticationRequestInterceptor(
			@Value("${accountService.apiKey}") String apiKey, @Value("${accountService.secret}") String secret) {
		return new BasicAuthRequestInterceptor(apiKey, secret);
	}

}
