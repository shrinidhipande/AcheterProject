package com.acheter.customer.controller.account;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.acheter.customer.form.account.ForgotPasswordForm;
import com.acheter.customer.form.account.ResetPasswordForm;
import com.acheter.customer.form.account.VerifyOtpForm;
import com.acheter.customer.service.account.AccountService;
import com.acheter.customer.validator.account.ForgotPasswordFormValidator;
import com.acheter.customer.validator.account.ResetPasswordFormValidator;

@Controller
@RequestMapping("/customer")
public class ForgotPasswordController {
	private final static Logger logger = LoggerFactory.getLogger(ForgotPasswordController.class);
	private final String VIEW_FORGOT_PASSWORD_PAGE = "forgot-password";
	private final String VIEW_VERIFY_OTP_PAGE = "verify-otp";
	private final String VIEW_ASK_VERIFY_EMAIL_LINK_PAGE = "ask-verify-email-link";
	private final String VIEW_RESET_PASSWORD_PAGE = "forgot-reset-password";
	private final String VIEW_PASSWORD_RESET_SUCCESS = "reset-password-success";
	private final String VIEW_VERIFY_EMAIL_LINK_ERROR = "verify-email-link-error";
	private final String VERIFY_FORGOTPASSOWRD_CODES_ERROR = "verify-forgotpassword-codes-error";

	private final String FORM_FORGOT_PASSWORD = "forgotPasswordForm";
	private final String FORM_VERIFY_OTP = "verifyOtpForm";
	private final String FORM_RESET_PASSWORD = "resetPasswordForm";

	private final String ATTR_VERIFICATION_MOBILE_NO = "verificationMobileNo";
	// private final String ATTR_USER_ACCOUNT_NO = "userAccountNo";

	@Autowired
	private ForgotPasswordFormValidator forgotPasswordFormValidator;

	@Autowired
	private ResetPasswordFormValidator resetPasswordFormValidator;

	@Autowired
	private AccountService accountService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/forgotpassword")
	public String showForgotPasswordPage(Model model) {
		ForgotPasswordForm form = null;

		form = new ForgotPasswordForm();
		model.addAttribute(FORM_FORGOT_PASSWORD, form);

		return VIEW_FORGOT_PASSWORD_PAGE;
	}

	@PostMapping("/forgotpassword")
	public String verifyEmailAddressOrMobileNo(
			@ModelAttribute("forgotPasswordForm") ForgotPasswordForm forgotPasswordForm, BindingResult errors,
			Model model) {
		String page = null;
		long userAccountNo = 0;
		VerifyOtpForm form = null;
		String verificationMobileNo = null;

		if (forgotPasswordFormValidator.supports(forgotPasswordForm.getClass())) {
			forgotPasswordFormValidator.validate(forgotPasswordForm, errors);
			if (errors.hasErrors()) {
				return VIEW_FORGOT_PASSWORD_PAGE;
			}
		}
		try {
			String no = accountService.verifyEmailAdressOrMobileNoForgotPassword(forgotPasswordForm);
			userAccountNo = Integer.parseInt(no);
		} catch (UserNotFoundException e) {
			errors.reject("usernameAndMobile.notfound");
			return VIEW_FORGOT_PASSWORD_PAGE;
		}

		if (forgotPasswordForm.getEmailAddress() != null && forgotPasswordForm.getEmailAddress().trim().length() > 0) {
			page = VIEW_ASK_VERIFY_EMAIL_LINK_PAGE;
		} else {
			form = new VerifyOtpForm();
			form.setUserAccountNo(userAccountNo);
			verificationMobileNo = "xxxxxxx" + forgotPasswordForm.getMobileNo().substring(
					forgotPasswordForm.getMobileNo().length() - 4, forgotPasswordForm.getMobileNo().length());
			model.addAttribute(ATTR_VERIFICATION_MOBILE_NO, verificationMobileNo);
			model.addAttribute(FORM_VERIFY_OTP, form);
			page = VIEW_VERIFY_OTP_PAGE;
		}

		return page;
	}

	@GetMapping("/forgotpassword/{accountNo}/{emailVerificationCode}/verifyEmail")
	public String verifyEmailCode(@PathVariable("accountNo") long userAccountNo,
			@PathVariable("emailVerificationCode") String emailVerficationCode, Model model) {
		ResetPasswordForm resetPasswordForm = null;
		UserAccountDto userAccountDto = null;

		try {
			userAccountDto = accountService.forgotPasswordVerifyEmail(String.valueOf(userAccountNo), emailVerficationCode);
		} catch (EmailVerificationFailedException e) {
			return VIEW_VERIFY_EMAIL_LINK_ERROR;
		}
		resetPasswordForm = new ResetPasswordForm();
		resetPasswordForm.setUserAccountNo(userAccountDto.getUserAccountNo());
		resetPasswordForm.setEmailVerificationCode(emailVerficationCode);

		model.addAttribute(FORM_RESET_PASSWORD, resetPasswordForm);
		return VIEW_RESET_PASSWORD_PAGE;
	}

	@PostMapping("/forgotpassword/verifyOTP")
	public String verifyOtp(@ModelAttribute("verifyOtpForm") @Valid VerifyOtpForm verifyOtpForm, BindingResult errors,
			Model model) {
		ResetPasswordForm resetPasswordForm = null;
		UserAccountDto userAccountDto = null;

		if (errors.hasErrors()) {
			return VIEW_VERIFY_OTP_PAGE;
		}
		try {
			userAccountDto = accountService.forgotPasswordVerifyOTP(String.valueOf(verifyOtpForm.getUserAccountNo()),
					verifyOtpForm.getOtp());
		} catch (OTPVerificationFailedException e) {
			errors.reject("otp.failed");
			return VIEW_VERIFY_OTP_PAGE;
		}
		resetPasswordForm = new ResetPasswordForm();
		resetPasswordForm.setUserAccountNo(userAccountDto.getUserAccountNo());
		resetPasswordForm.setOtpCode(verifyOtpForm.getOtp());

		model.addAttribute(FORM_RESET_PASSWORD, resetPasswordForm);
		return VIEW_RESET_PASSWORD_PAGE;
	}

	@PostMapping("/forgotpassword/reset")
	public String resetPassword(@ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,
			BindingResult errors, Model model) {
		boolean checkCodes = false;

		if (resetPasswordFormValidator.supports(resetPasswordForm.getClass())) {
			resetPasswordFormValidator.validate(resetPasswordForm, errors);
			if (errors.hasErrors()) {
				return VIEW_RESET_PASSWORD_PAGE;
			}
		}
		if (resetPasswordForm.getOtpCode() != null && resetPasswordForm.getOtpCode().trim().length() > 0) {
			try {
				accountService.forgotPasswordVerifyOTP(String.valueOf(resetPasswordForm.getUserAccountNo()),
						resetPasswordForm.getOtpCode());
				checkCodes = true;
				logger.debug("otp verification successful, reseting the user password");
			} catch (OTPVerificationFailedException e) {
				logger.error("otp verification code failed, redirecting to error page", e);
				return VERIFY_FORGOTPASSOWRD_CODES_ERROR;
			}
		}
		if (resetPasswordForm.getEmailVerificationCode() != null
				&& resetPasswordForm.getEmailVerificationCode().trim().length() > 0) {

			try {
				accountService.forgotPasswordVerifyEmail(String.valueOf(resetPasswordForm.getUserAccountNo()),
						resetPasswordForm.getEmailVerificationCode());
				logger.debug("email verification successful, resetting the password");
				checkCodes = true;
			} catch (EmailVerificationFailedException e) {
				logger.error("email verification code failed, redirecting to error page", e);
				return VERIFY_FORGOTPASSOWRD_CODES_ERROR;
			}
		}
		if (checkCodes == false) {
			logger.error("there is no otp or email verification found, so taking the user to error page");
			return VERIFY_FORGOTPASSOWRD_CODES_ERROR;
		}

		resetPasswordForm.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword()));
		accountService.resetPassword(resetPasswordForm);

		return VIEW_PASSWORD_RESET_SUCCESS;
	}

}
