package com.acheter.customer.service.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.acheter.customer.dto.account.UserAccountDto;
import com.acheter.customer.dto.account.UserAccountVerificationStatusDto;
import com.acheter.customer.feign.configuration.FeignAccountServiceClientConfiguration;
import com.acheter.customer.form.account.ForgotPasswordForm;
import com.acheter.customer.form.account.ResetPasswordForm;

@FeignClient(name = "accountService", url = "${accountService.url}/account" , configuration=FeignAccountServiceClientConfiguration.class)
public interface AccountService {
	@GetMapping(value = "/emailAddress/count", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String countCustomerByEmailAddress(@RequestParam("emailAddress") String emailAddress);

	@GetMapping(value = "/mobileNo/count", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String countCustomerByMobileNo(@RequestParam("mobileNo") String mobileNo);

	@GetMapping(value = "/displayName/count", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String countCustomerByDisplayName(@RequestParam("displayName") String displayName);

	@PostMapping(value = "/customer/new", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public String newCustomer(@RequestBody UserAccountDto userAccount);

	@PutMapping(value = "/forgotpassword", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String verifyEmailAdressOrMobileNoForgotPassword(ForgotPasswordForm forgotPassword);

	@GetMapping(value = "/forgotpassword/{userAccountNo}/{otpCode}/verifyOTP", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserAccountDto forgotPasswordVerifyOTP(@PathVariable("userAccountNo") String userAccountNo,
			@PathVariable("otpCode") String otp);

	@GetMapping(value = "/forgotpassword/{userAccountNo}/{emailVerificationCode}/verifyEmail", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserAccountDto forgotPasswordVerifyEmail(@PathVariable("userAccountNo") String userAccountNo,
			@PathVariable("emailVerificationCode") String emailVerificationCode);

	@PutMapping(value = "/resetpassword", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void resetPassword(@RequestBody ResetPasswordForm resetPasswordForm);

	@GetMapping(value = "/email/{emailAddress}/details", produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserAccountDto getUserAccount(@PathVariable("emailAddress") String emailAddress);

	@GetMapping(value = "/accountNo/{userAccountNo}/details", produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserAccountDto getUserAccount(@PathVariable("userAccountNo") long userAccountNo);

	@GetMapping(value = "/new/{userAccountNo}/{emailVerificationCode}/verifyEmail", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserAccountVerificationStatusDto verifyEmailAddressAndActivateAccount(
			@PathVariable("userAccountNo") long userAccountNo,
			@PathVariable("emailVerificationCode") String emailVerificationCode);

	@GetMapping(value = "/new/{userAccountNo}/{otpCode}/verifyOtp", produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserAccountVerificationStatusDto verifyOtpCodeAndActivateAccount(
			@PathVariable("userAccountNo") long userAccountNo, @PathVariable("otpCode") String otpCode);
}






















