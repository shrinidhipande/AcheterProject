package com.acheter.accountservice.api.account;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acheter.accountservice.dto.account.AccountVerificationStatusDto;
import com.acheter.accountservice.dto.account.ForgotPasswordDto;
import com.acheter.accountservice.dto.account.ResetPasswordDto;
import com.acheter.accountservice.dto.account.UserAccountDto;
import com.acheter.accountservice.exception.AcheterError;
import com.acheter.accountservice.exception.EmailNotificationFailedException;
import com.acheter.accountservice.exception.UserAccountNotFoundException;
import com.acheter.accountservice.exception.VerificationCodeMismatchException;
import com.acheter.accountservice.service.account.AccountService;
import com.acheter.accountservice.utils.AccountServiceConstants;

@RestController
@RequestMapping("/account")
public class ManageUserAccountApiService {
	private final static Logger logger = LoggerFactory.getLogger(ManageUserAccountApiService.class);
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MessageSource messageSource;

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

	@GetMapping("/emailAddress/count")
	public ResponseEntity<String> countCustomersByEmailAddress(
			final @RequestParam("emailAddress") String emailAddress) {
		logger.debug("received api call for counting customers based on email address : {}", emailAddress);
		long count = accountService.countAccountsByEmailAddress(emailAddress);
		logger.info("found {} customer accounts for an email address: {}", count, emailAddress);
		return ResponseEntity.ok(String.valueOf(count));
	}

	@GetMapping("/mobileNo/count")
	public ResponseEntity<String> countCustomersByMobileNo(final @RequestParam("mobileNo") String mobileNo) {
		logger.debug("received api call for counting customers based on mobileNo : {}", mobileNo);
		long count = accountService.countAccountsByMobileNo(mobileNo);
		logger.info("found {} customer accounts for an mobileNo: {}", count, mobileNo);
		return ResponseEntity.ok(String.valueOf(count));
	}

	@GetMapping("/displayName/count")
	public ResponseEntity<String> countCustomersByDisplayName(final @RequestParam("displayName") String displayName) {
		logger.debug("received api call for counting customers based on displayName : {}", displayName);
		long count = accountService.countAccountsByDisplayName(displayName);
		logger.info("found {} customer accounts for an displayName: {}", count, displayName);
		return ResponseEntity.ok(String.valueOf(count));
	}
	
	
	@GetMapping(value = "/new/{userAccountNo}/{emailVerificationCode}/verifyEmail", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountVerificationStatusDto> verifyEmailAddressAndActivateAccount(
			@PathVariable("userAccountNo") long userAccountNo,
			@PathVariable("emailVerificationCode") String emailVerificationCode)
			throws VerificationCodeMismatchException {
		AccountVerificationStatusDto accountVerificationStatusDto = null;

		accountVerificationStatusDto = accountService.verifyEmailAddressAndActivateAccount(userAccountNo,
				emailVerificationCode);

		return ResponseEntity.ok(accountVerificationStatusDto);
	}

	@GetMapping(value = "/new/{userAccountNo}/{otpCode}/verifyOtp", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AccountVerificationStatusDto> verifyOtpCodeAndActivateAccount(
			@PathVariable("userAccountNo") long userAccountNo, @PathVariable("otpCode") String otpCode)
			throws VerificationCodeMismatchException {
		AccountVerificationStatusDto accountVerificationStatusDto = null;

		accountVerificationStatusDto = accountService.verifyOtpCodeAndActivateAccount(userAccountNo, otpCode);
		return ResponseEntity.ok(accountVerificationStatusDto);
	}

	
	/*
	 * Forgot Password API
	 * -----------------------------------------------------------------------------------------------------
	 * */
	
	@PutMapping(value="/forgotpassword", consumes= {MediaType.APPLICATION_JSON_VALUE}, produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> userForgotPasswordRegenerateCode(@RequestBody ForgotPasswordDto forgotPasswordDto) throws UserAccountNotFoundException{
		
		long userAccountNo = 0;

		logger.trace("received api call for verifying and regenerating the code");
		userAccountNo = accountService.verifyUserDetailsAndRegenerateCode(forgotPasswordDto);
		logger.debug("found system user id :{} and regenerated the codes", userAccountNo);

		return ResponseEntity.ok(String.valueOf(userAccountNo));
		
	}
	@GetMapping(value = "/forgotpassword/{userAccountNo}/{emailVerificationCode}/verifyEmail", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserAccountDto> forgotPasswordVerifyEmailVerificationCode(
			@PathVariable("userAccountNo") long userAccountNo,
			@PathVariable("emailVerificationCode") String emailVerficiationCode)
			throws VerificationCodeMismatchException, UserAccountNotFoundException {
		UserAccountDto userAccountDto = null;
		AcheterError error = null;
		boolean match = false;

		match = accountService.matchSystemUserEmailVerificationCode(userAccountNo, emailVerficiationCode);
		if (match == false) {
			error = new AcheterError(AccountServiceConstants.ERR_CODE_EMAIL_VERIFICATION_FAILED,
					messageSource.getMessage(AccountServiceConstants.ERR_CODE_EMAIL_VERIFICATION_FAILED_KEY, null,
							Locale.getDefault()));
			throw new VerificationCodeMismatchException("email verification code failed", error);
		}
		userAccountDto = accountService.getUserAccount(userAccountNo);

		return ResponseEntity.ok(userAccountDto);
	}

	@GetMapping(value = "/forgotpassword/{userAccountNo}/{otpCode}/verifyOTP", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserAccountDto> forgotPasswordVerifyOtpCode(@PathVariable("userAccountNo") long userAccountNo,
			@PathVariable("otpCode") String otpCode)
			throws VerificationCodeMismatchException, UserAccountNotFoundException {
		UserAccountDto userAccountDto = null;
		AcheterError error = null;
		boolean match = false;

		match = accountService.matchSystemUserOtpCode(userAccountNo, otpCode);
		if (match == false) {
			error = new AcheterError(AccountServiceConstants.ERR_CODE_OTP_CODE_FAILED, messageSource
					.getMessage(AccountServiceConstants.ERR_CODE_OTP_CODE_FAILED_KEY, null, Locale.getDefault()));
			throw new VerificationCodeMismatchException("otp code failed", error);
		}
		userAccountDto = accountService.getUserAccount(userAccountNo);

		return ResponseEntity.ok(userAccountDto);
	}
	
	@PutMapping(value = "/resetpassword", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
		int c = 0;

		c = accountService.updatePassword(resetPasswordDto.getUserAccountNo(), resetPasswordDto.getPassword());
		logger.debug("user account password updated : {} ", c);
		if (c > 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value = "/email/{emailAddress}/details", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserAccountDto> getUserAccount(@PathVariable("emailAddress") String emailAddress)
			throws UserAccountNotFoundException {
		logger.info("received api request for fetching user account based on emailAddress : {}", emailAddress);
		return ResponseEntity.ok(accountService.getUserAccount(emailAddress));
	}

	@GetMapping(value = "/accountNo/{userAccountNo}/details", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserAccountDto> getUserAccount(@PathVariable("userAccountNo") long userAccountNo)
			throws UserAccountNotFoundException {
		logger.info("received api request for fetching user account based on userAccountNo : {}", userAccountNo);
		return ResponseEntity.ok(accountService.getUserAccount(userAccountNo));
	}
	
	@ExceptionHandler({ UserAccountNotFoundException.class })
	public ResponseEntity<List<AcheterError>> handleUserAccountNotFoundException(UserAccountNotFoundException e) {
		logger.error("reporting error response for user account not found exception");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(e.getErrors());
	}

	@ExceptionHandler({ VerificationCodeMismatchException.class })
	public ResponseEntity<List<AcheterError>> handleVerificationCodeMisMatchException(
			VerificationCodeMismatchException e) {
		logger.error("reporting error response for user account not found exception");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(e.getErrors());
	}

	@ExceptionHandler
	public ResponseEntity<List<AcheterError>> handleEmailNotificationFailedException(
			EmailNotificationFailedException e) {
		logger.error("reporting error response for email notification failed exception");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(e.getErrors());

	}
}
