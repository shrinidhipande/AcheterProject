package com.acheter.customer.feign.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class AdsTradingServiceClientConfiguration {

	@Bean
	public BasicAuthRequestInterceptor authenticationInterceptor(@Value("${adsTradingService.apiKey}") String apiKey,
			@Value("${adsTradingService.secret}") String secret) {
		BasicAuthRequestInterceptor authRequestInterceptor = null;

		System.out.println("apiKey :" + apiKey);
		authRequestInterceptor = new BasicAuthRequestInterceptor(apiKey, secret);
		return authRequestInterceptor;
	}
}
