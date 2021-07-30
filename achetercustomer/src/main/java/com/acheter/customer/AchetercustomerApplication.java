package com.acheter.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients
public class AchetercustomerApplication implements WebMvcConfigurer {
	private final static Logger logger = LoggerFactory.getLogger(AchetercustomerApplication.class);

	public static void main(String[] args) {
		logger.info("starting acheter customer application");
		SpringApplication.run(AchetercustomerApplication.class, args);
	}
	
	/*
	 * @Bean public MessageSource messageSource() {
	 * 
	 * ResourceBundleMessageSource messageSource = null; messageSource = new
	 * ResourceBundleMessageSource();
	 * 
	 * messageSource.setBasenames("errors");
	 * 
	 * return messageSource;
	 * 
	 * }
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// registry.addViewController("/register").setViewName("register");
	}
}
