
package com.acheter.customer.controller.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acheter.customer.form.account.ForgotPasswordForm;
import com.acheter.customer.form.account.ResetPasswordForm;
import com.acheter.customer.form.account.VerifyOtpForm;
import com.acheter.customer.validator.account.ForgotPasswordFormValidator;
import com.acheter.customer.validator.account.ResetPasswordFormValidator;

@Controller
@RequestMapping("/customer")
public class ForgotPasswordController {
	private final String VIEW_FORGOT_PASSWORD_PAGE = "forgot-password";
	private final String VIEW_VERIFY_OTP_PAGE = "verify-otp";
	private final String VIEW_ASK_VERIFY_EMAIL_LINK_PAGE = "ask-verify-email-link";
	private final String VIEW_RESET_PASSWORD_PAGE = "reset-password";
	private final String VIEW_PASSWORD_RESET_SUCCESS = "reset-password-success";
	private final String VIEW_VERIFY_EMAIL_LINK_ERROR = "verify-email-link-error";

	private final String FORM_FORGOT_PASSWORD = "forgotPasswordForm";
	private final String FORM_VERIFY_OTP = "verifyOtpForm";
	private final String FORM_RESET_PASSWORD = "resetPasswordForm";

	private final String ATTR_VERIFICATION_MOBILE_NO = "verificationMobileNo";

	@Autowired
	private ForgotPasswordFormValidator forgotPasswordFormValidator;
	@Autowired
	private ResetPasswordFormValidator resetPasswordFormValidator;

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
		String verificationMobileNo = null;

		if (forgotPasswordFormValidator.supports(forgotPasswordForm.getClass())) {
			forgotPasswordFormValidator.validate(forgotPasswordForm, errors);
			if (errors.hasErrors()) {
				return VIEW_FORGOT_PASSWORD_PAGE;
			}
		}
		if (forgotPasswordForm.getEmailAddress() != null && forgotPasswordForm.getEmailAddress().trim().length() > 0) {
			page = VIEW_ASK_VERIFY_EMAIL_LINK_PAGE;
		} else {
			verificationMobileNo = "xxxxxxx" + forgotPasswordForm.getMobileNo().substring(
					forgotPasswordForm.getMobileNo().length() - 4, forgotPasswordForm.getMobileNo().length());
			model.addAttribute(ATTR_VERIFICATION_MOBILE_NO, verificationMobileNo);
			model.addAttribute(FORM_VERIFY_OTP, new VerifyOtpForm());
			page = VIEW_VERIFY_OTP_PAGE;
		}

		return page;
	}

	@GetMapping("/forgotpassword/{accountNo}/{emailVerificationCode}/verifyEmail")
	public String verifyEmailCode(@PathVariable("accountNo") long userAccountNo,
			@PathVariable("emailVerificationCode") String emailVerficationCode, Model model) {
		ResetPasswordForm resetPasswordForm = null;
		
		if(userAccountNo != 10 || emailVerficationCode.equals("ec1") == false) {
			return VIEW_VERIFY_EMAIL_LINK_ERROR;
		}
		
		resetPasswordForm = new ResetPasswordForm();
		resetPasswordForm.setUserAccountNo(userAccountNo);

		model.addAttribute(FORM_RESET_PASSWORD, resetPasswordForm);
		return VIEW_RESET_PASSWORD_PAGE;
	}

	@PostMapping("/forgotpassword/verifyOTP")
	public String verifyOtp(@ModelAttribute("verifyOtpForm") @Valid VerifyOtpForm verifyOtpForm, BindingResult errors,
			Model model) {
		ResetPasswordForm resetPasswordForm = null;
		if (errors.hasErrors()) {
			return VIEW_VERIFY_OTP_PAGE;
		}
		resetPasswordForm = new ResetPasswordForm();
		resetPasswordForm.setUserAccountNo(verifyOtpForm.getUserAccountNo());

		model.addAttribute(FORM_RESET_PASSWORD, resetPasswordForm);
		return VIEW_RESET_PASSWORD_PAGE;
	}

	@PostMapping("/forgotpassword/reset")
	public String resetPassword(@ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,
			BindingResult errors, Model model) {

		if (resetPasswordFormValidator.supports(resetPasswordForm.getClass())) {
			resetPasswordFormValidator.validate(resetPasswordForm, errors);
			if (errors.hasErrors()) {
				return VIEW_RESET_PASSWORD_PAGE;
			}
		}

		return VIEW_PASSWORD_RESET_SUCCESS;
	}

}