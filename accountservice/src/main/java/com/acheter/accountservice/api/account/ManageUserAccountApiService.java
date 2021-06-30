package com.acheter.accountservice.api.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acheter.accountservice.dto.account.UserAccountDto;
import com.acheter.accountservice.service.account.AccountService;

@RestController
@RequestMapping("/account")
public class ManageUserAccountApiService {
	private final static Logger logger = LoggerFactory.getLogger(ManageUserAccountApiService.class);
	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/customer/new", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> newCustomer(@RequestBody UserAccountDto userAccount) {
		long accountNo = 0;
		logger.trace("entered into newCustomer() method");

		logger.debug("user account data received {}", userAccount);
		accountNo = accountService.registerCustomer(userAccount);
		logger.info("registered customer with customer no : {}", accountNo);

		return ResponseEntity.ok(String.valueOf(accountNo));
	}

	@GetMapping("/customer/emailAddress/count")
	public ResponseEntity<String> countCustomersByEmailAddress(
			final @RequestParam("emailAddress") String emailAddress) {
		logger.debug("received api call for counting customers based on email address : {}", emailAddress);
		long count = accountService.countAccountsByEmailAddress(emailAddress);
		logger.info("found {} customer accounts for an email address: {}", count, emailAddress);
		return ResponseEntity.ok(String.valueOf(count));
	}

	@GetMapping("/customer/mobileNo/count")
	public ResponseEntity<String> countCustomersByMobileNo(final @RequestParam("mobileNo") String mobileNo) {
		logger.debug("received api call for counting customers based on mobileNo : {}", mobileNo);
		long count = accountService.countAccountsByMobileNo(mobileNo);
		logger.info("found {} customer accounts for an mobileNo: {}", count, mobileNo);
		return ResponseEntity.ok(String.valueOf(count));
	}

	@GetMapping("/customer/displayName/count")
	public ResponseEntity<String> countCustomersByDisplayName(final @RequestParam("displayName") String displayName) {
		logger.debug("received api call for counting customers based on displayName : {}", displayName);
		long count = accountService.countAccountsByDisplayName(displayName);
		logger.info("found {} customer accounts for an displayName: {}", count, displayName);
		return ResponseEntity.ok(String.valueOf(count));
	}
	
	
	
	

}
