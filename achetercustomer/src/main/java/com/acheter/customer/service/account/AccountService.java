package com.acheter.customer.service.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.acheter.customer.dto.UserAccountDto;

@FeignClient(name = "accountService", url = "${accountService.url}")
public interface AccountService {
	@GetMapping(value = "/customer/emailAddress/count", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String countCustomerByEmailAddress(@RequestParam("emailAddress") String emailAddress);
	
	@GetMapping(value = "/customer/mobileNo/count", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String countCustomerByMobileNo(@RequestParam("mobileNo") String mobileNo);

	@GetMapping(value = "/customer/displayName/count", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String countCustomerByDisplayName(@RequestParam("displayName") String displayName);

	
	@PostMapping(value = "/customer/new", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public String newCustomer(@RequestBody UserAccountDto userAccount);
}
