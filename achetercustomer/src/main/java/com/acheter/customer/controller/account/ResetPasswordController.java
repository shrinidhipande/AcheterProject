package com.acheter.customer.controller.account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acheter.customer.beans.security.UserDetailsImpl;
import com.acheter.customer.form.account.ResetPasswordForm;
import com.acheter.customer.service.account.AccountService;
import com.acheter.customer.validator.account.ResetPasswordFormValidator;

/**
 * ResetPassword flow begins post login of the user into application
 * 
 * @author sriman
 *
 */
@Controller
@RequestMapping("/resetpassword")
public class ResetPasswordController {
	private final static Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

	private final String FORM_RESET_PASSWORD = "resetPasswordForm";
	private final String VIEW_RESET_PASSWORD = "reset-password";
	private final String VIEW_RESET_PASSWORD_SUCCESS = "reset-password-success";

	@Autowired
	private AccountService accountService;

	@Autowired
	private ResetPasswordFormValidator resetPasswordFormValidator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@InitBinder
	public void init(WebDataBinder dataBinder) {
		dataBinder.addValidators(resetPasswordFormValidator);
	}

	@GetMapping
	public String showResetPasswordForm(Model model) {
		ResetPasswordForm resetPasswordForm = null;
		UserDetailsImpl userDetailsImpl = null;
		Object principle = null;

		principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principle instanceof UserDetails) {
			userDetailsImpl = (UserDetailsImpl) principle; // security context get the user account no
		}

		resetPasswordForm = new ResetPasswordForm();
		resetPasswordForm.setUserAccountNo(userDetailsImpl.getUserAccountNo());
		model.addAttribute(FORM_RESET_PASSWORD, resetPasswordForm);

		return VIEW_RESET_PASSWORD;
	}

	@PostMapping
	public String resetPassword(@ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,
			BindingResult errors, Model model, HttpServletRequest request) {
		if (errors.hasErrors()) {
			return VIEW_RESET_PASSWORD;
		}
		resetPasswordForm.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword()));
		accountService.resetPassword(resetPasswordForm);
		try {
			request.logout();// will logout the user
			SecurityContextHolder.clearContext();
			if (request.getSession(false) != null) {
				request.getSession(false).invalidate();
			}
		} catch (ServletException e) {
			logger.error("error while logging out the user during reset password", e);
		}

		return VIEW_RESET_PASSWORD_SUCCESS;
	}

}
