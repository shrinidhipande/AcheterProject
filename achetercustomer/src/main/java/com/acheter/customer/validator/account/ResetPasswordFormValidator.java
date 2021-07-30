package com.acheter.customer.validator.account;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.acheter.customer.form.account.ResetPasswordForm;

@Component
public class ResetPasswordFormValidator implements Validator {
	private final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ResetPasswordForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ResetPasswordForm form = null;

		form = (ResetPasswordForm) target;
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
	}

}
