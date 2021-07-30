package com.acheter.accountservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.notificationsdk.*")
public class AccountserviceApplication {
	private final static Logger logger = LoggerFactory.getLogger(AccountserviceApplication.class);

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		logger.debug("starting account service application");
		SpringApplication.run(AccountserviceApplication.class, args);
	}

}
