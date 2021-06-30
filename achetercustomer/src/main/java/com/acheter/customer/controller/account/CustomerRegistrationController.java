package com.acheter.customer.controller.account;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acheter.customer.dto.UserAccountDto;
import com.acheter.customer.form.account.CustomerRegistrationForm;
import com.acheter.customer.service.account.AccountService;
import com.acheter.customer.validator.account.CustomerRegistrationFormValidator;

@Controller
@RequestMapping("/customer")
public class CustomerRegistrationController {
	private final static Logger logger = LoggerFactory.getLogger(CustomerRegistrationController.class);
	private final String VIEW_REGISTER = "register";
	private final String VIEW_VERIFY_REGISTRATION = "verify-registration";

	private final String ATTR_VERFICATION_EMAIL_ADDRESS = "verificationEmailAddress";
	private final String ATTR_VERIFICATION_MOBILE_NO = "verificationMobileNo";

	@Autowired
	private CustomerRegistrationFormValidator validator;
	@Autowired
	private AccountService accountService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(validator);
	}

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
		UserAccountDto userAccount = null;
		String verificationMobileNo = null;
		String verificationEmailAddress = null;

		if (errors.hasErrors()) {
			logger.debug("found {} errors in customerRegistrationForm so redisplaying source page of lvn {}",
					errors.getFieldErrorCount(), VIEW_REGISTER);
			return VIEW_REGISTER;
		}

		userAccount = new UserAccountDto();
		userAccount.setEmailAddress(form.getEmailAddress());
		userAccount.setFirstName(form.getFirstName());
		userAccount.setLastName(form.getLastName());
		userAccount.setDisplayName(form.getDisplayName());
		userAccount.setMobileNo(form.getMobileNo());
		userAccount.setPassword(form.getPassword());

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
		logger.debug("displaying registration success page with lvn {}", VIEW_VERIFY_REGISTRATION);
		return VIEW_VERIFY_REGISTRATION;
	}

}
