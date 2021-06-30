package com.acheter.customer.validator.account;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.acheter.customer.form.account.CustomerRegistrationForm;
import com.acheter.customer.service.account.AccountService;

@Component
public class CustomerRegistrationFormValidator implements Validator {
	private final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	@Autowired
	private AccountService accountService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(CustomerRegistrationForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomerRegistrationForm form = null;
		long countAccounts = 0;

		form = (CustomerRegistrationForm) target;

		if (errors.hasFieldErrors("password") == false) {
			if (Pattern.matches(PASSWORD_REGEX, form.getPassword()) == false) {
				errors.rejectValue("password", "password.notvalid");
			}

			if (errors.hasFieldErrors("reTypePassword") == false) {

				if (form.getPassword().equals(form.getReTypePassword()) == false) {
					errors.rejectValue("reTypePassword", "reTypePassword.mismatch");
				}
			}
		}
		if (errors.hasFieldErrors("emailAddress") == false) {
			countAccounts = Integer.parseInt(accountService.countCustomerByEmailAddress(form.getEmailAddress()));
			if (countAccounts > 0) {
				errors.rejectValue("emailAddress", "emailAddress.notAvailable");
			}
		}
		if (errors.hasFieldErrors("mobileNo") == false) {
			countAccounts = Integer.parseInt(accountService.countCustomerByMobileNo(form.getMobileNo()));
			if (countAccounts > 0) {
				errors.rejectValue("mobileNo", "mobileNo.notAvailable");
			}
		}
		if(errors.hasFieldErrors("displayName") == false) {
			countAccounts = Integer.parseInt(accountService.countCustomerByDisplayName(form.getDisplayName()));
			if (countAccounts > 0) {
				errors.rejectValue("displayName", "displayName.notAvailable");
			}
		}

	}

}
