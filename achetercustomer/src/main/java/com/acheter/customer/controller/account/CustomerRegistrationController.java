package com.acheter.customer.controller.account;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acheter.customer.accountservice.exception.EmailVerificationFailedException;
import com.acheter.customer.accountservice.exception.OTPVerificationFailedException;
import com.acheter.customer.accountservice.exception.UserNotFoundException;
import com.acheter.customer.dto.account.UserAccountDto;
import com.acheter.customer.dto.account.UserAccountVerificationStatusDto;
import com.acheter.customer.form.account.CustomerRegistrationForm;
import com.acheter.customer.form.account.VerifyOtpForm;
import com.acheter.customer.service.account.AccountService;
import com.acheter.customer.validator.account.CustomerRegistrationFormValidator;

@Controller
@RequestMapping("/customer")
public class CustomerRegistrationController {
	private final static Logger logger = LoggerFactory.getLogger(CustomerRegistrationController.class);
	private final String VIEW_REGISTER = "register";
	private final String VIEW_VERIFY_REGISTER_OTP = "register-verify-otp";
	private final String VIEW_VERIFY_ACCOUNT_ACTIVATED = "user-account-activated";
	private final String VIEW_VERIFY_EMAIL_LINK_ERROR = "verify-email-link-error";

	private final String ATTR_VERFICATION_EMAIL_ADDRESS = "verificationEmailAddress";
	private final String ATTR_VERIFICATION_MOBILE_NO = "verificationMobileNo";

	private final String FORM_VERIFY_OTP = "verifyOtpForm";

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CustomerRegistrationFormValidator validator;

	@Autowired
	private AccountService accountService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String showCustomerRegistrationPage(Model model) {
		CustomerRegistrationForm customerRegistrationForm = null;

		logger.trace("entered into showCustomerRegistrationPage() method");
		customerRegistrationForm = new CustomerRegistrationForm();
		model.addAttribute("customerRegistrationForm", customerRegistrationForm);
		logger.debug("displaying the registration page to the customer by returning lvn as {}", VIEW_REGISTER);
		return VIEW_REGISTER;
	}

	@PostMapping("/register")
	public String registerCustomer(@ModelAttribute("customerRegistrationForm") @Valid CustomerRegistrationForm form,
			BindingResult errors, Model model) {
		String accountNo = null;
		VerifyOtpForm verifyOtpForm = null;
		UserAccountDto userAccount = null;
		String verificationMobileNo = null;
		String verificationEmailAddress = null;

		if (validator.supports(form.getClass())) {
			validator.validate(form, errors);
			if (errors.hasErrors()) {
				logger.debug("found {} errors in customerRegistrationForm so redisplaying source page of lvn {}",
						errors.getFieldErrorCount(), VIEW_REGISTER);
				return VIEW_REGISTER;
			}
		}

		userAccount = new UserAccountDto();
		userAccount.setEmailAddress(form.getEmailAddress());
		userAccount.setFirstName(form.getFirstName());
		userAccount.setLastName(form.getLastName());
		userAccount.setDisplayName(form.getDisplayName());
		userAccount.setMobileNo(form.getMobileNo());
		userAccount.setPassword(passwordEncoder.encode(form.getPassword()));

		logger.debug("invoke account service to register customer of email address : {}", form.getEmailAddress());
		accountNo = accountService.newCustomer(userAccount);
		logger.info("stored customer with customer no {} of the user email address {}", accountNo,
				form.getEmailAddress());

		verificationEmailAddress = form.getEmailAddress().substring(0, 4) + "xxxxx" + form.getEmailAddress()
				.substring(form.getEmailAddress().lastIndexOf("@"), form.getEmailAddress().length());
		verificationMobileNo = "xxxxxxx"
				+ form.getMobileNo().substring(form.getMobileNo().length() - 4, form.getMobileNo().length());

		model.addAttribute(ATTR_VERFICATION_EMAIL_ADDRESS, verificationEmailAddress);
		model.addAttribute(ATTR_VERIFICATION_MOBILE_NO, verificationMobileNo);
		logger.debug("displaying registration success page with lvn {}", VIEW_VERIFY_REGISTER_OTP);

		verifyOtpForm = new VerifyOtpForm();
		verifyOtpForm.setUserAccountNo(Long.parseLong(accountNo));
		model.addAttribute(FORM_VERIFY_OTP, verifyOtpForm);
		return VIEW_VERIFY_REGISTER_OTP;
	}

	@GetMapping("/register/{userAccountNo}/{verificationCode}/email")
	public String verifyEmailAddress(@PathVariable("userAccountNo") long userAccountNo,
			@PathVariable("verificationCode") String emailVerificationCode, Model model) {
		UserAccountVerificationStatusDto userAccountVerificationStatusDto = null;
		String message = null;
		VerifyOtpForm form = null;
		UserAccountDto userAccount = null;

		logger.debug("userAccountNo : {} and verification code :{} for verifying email", userAccountNo,
				emailVerificationCode);
		try {
			userAccount = accountService.getUserAccount(userAccountNo);
			if (userAccount.getEmailVerificationStatus() == 1 && userAccount.getOtpCodeStatus() == 1) {
				message = messageSource.getMessage("userAccount.alreadyActivated", null, Locale.getDefault());
				model.addAttribute("accountActivatedMessage", message);
				return VIEW_VERIFY_ACCOUNT_ACTIVATED;
			} else if (userAccount.getEmailVerificationStatus() == 1) {
				message = messageSource.getMessage("userAccount.emailAddressAlreadyVerified", null,
						Locale.getDefault());
				model.addAttribute("accountActivatedMessage", message);
				return VIEW_VERIFY_ACCOUNT_ACTIVATED;
			}

		} catch (UserNotFoundException e) {
			logger.error("user account no : {} not found for verifying the email verification code", userAccountNo, e);
			return VIEW_VERIFY_EMAIL_LINK_ERROR;
		}

		try {
			userAccountVerificationStatusDto = accountService.verifyEmailAddressAndActivateAccount(userAccountNo,
					emailVerificationCode);
		} catch (EmailVerificationFailedException e) {
			logger.error("email verification code mis-match while activating the register user of accountNo {}",
					userAccountNo, e);
			return VIEW_VERIFY_EMAIL_LINK_ERROR;
		}

		if (userAccountVerificationStatusDto.getEmailVerificationStatus() == 1
				&& userAccountVerificationStatusDto.getOtpCodeStatus() == 1) {
			message = messageSource.getMessage("userAccount.activated", null, Locale.getDefault());
			model.addAttribute("accountActivatedMessage", message);
			return VIEW_VERIFY_ACCOUNT_ACTIVATED;
		}

		form = new VerifyOtpForm();
		form.setUserAccountNo(userAccountNo);
		model.addAttribute(FORM_VERIFY_OTP, form);

		return VIEW_VERIFY_REGISTER_OTP;
	}

	@PostMapping("/new/verifyOTP")
	public String verifyOTP(@ModelAttribute("verifyOtpForm") VerifyOtpForm verifyOtpForm, BindingResult errors,
			Model model) {
		UserAccountVerificationStatusDto userAccountVerificationStatusDto = null;
		UserAccountDto userAccount = null;
		String message = null;

		if (errors.hasErrors()) {
			return VIEW_VERIFY_REGISTER_OTP;
		}
		
		try {
			userAccount = accountService.getUserAccount(verifyOtpForm.getUserAccountNo());
			if (userAccount.getEmailVerificationStatus() == 1 && userAccount.getOtpCodeStatus() == 1) {
				message = messageSource.getMessage("userAccount.alreadyActivated", null, Locale.getDefault());
				model.addAttribute("accountActivatedMessage", message);
				return VIEW_VERIFY_ACCOUNT_ACTIVATED;
			} else if (userAccount.getOtpCodeStatus() == 1) {
				message = messageSource.getMessage("userAccount.otpAlreadyVerified", null, Locale.getDefault());
				model.addAttribute("accountActivatedMessage", message);
				return VIEW_VERIFY_ACCOUNT_ACTIVATED;
			}

		} catch (UserNotFoundException e) {
			logger.error("user account no : {} not found for verifying the email verification code",
					verifyOtpForm.getUserAccountNo(), e);
			errors.reject("otp.failed");
			return VIEW_VERIFY_REGISTER_OTP;
		}
		try {
			userAccountVerificationStatusDto = accountService
					.verifyOtpCodeAndActivateAccount(verifyOtpForm.getUserAccountNo(), verifyOtpForm.getOtp());
		} catch (OTPVerificationFailedException e) {
			logger.error("email verification code mis-match while activating the register user of accountNo {}",
					verifyOtpForm.getUserAccountNo(), e);
			errors.reject("otp.failed");
			return VIEW_VERIFY_REGISTER_OTP;
		}

		if (userAccountVerificationStatusDto.getEmailVerificationStatus() == 1
				&& userAccountVerificationStatusDto.getOtpCodeStatus() == 1) {
			message = messageSource.getMessage("userAccount.activated", null, Locale.getDefault());
			model.addAttribute("accountActivatedMessage", message);
			return VIEW_VERIFY_ACCOUNT_ACTIVATED;
		}
		
		message = messageSource.getMessage("otpCode.verified", null, Locale.getDefault());
		model.addAttribute("accountActivatedMessage", message);
		return VIEW_VERIFY_ACCOUNT_ACTIVATED;
	}

}
