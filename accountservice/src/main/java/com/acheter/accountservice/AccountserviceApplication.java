package com.acheter.accountservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AccountserviceApplication {

	private final static Logger logger = LoggerFactory.getLogger(AccountserviceApplication.class);
	public static void main(String[] args) {
		logger.debug("starting account service application");
		SpringApplication.run(AccountserviceApplication.class, args);
	}

}
