package com.acheter.customer.validator.account;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.acheter.customer.form.account.ForgotPasswordForm;

@Component
public class ForgotPasswordFormValidator implements Validator {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ForgotPasswordForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ForgotPasswordForm form = null;

		form = (ForgotPasswordForm) target;

		if ((form.getEmailAddress() == null || form.getEmailAddress().trim().length() == 0)
				&& (form.getMobileNo() == null || form.getMobileNo().trim().length() == 0)) {
			errors.reject("emailOrMobile.required");
		}

		if (form.getEmailAddress() != null && form.getEmailAddress().trim().length() > 0) {
			if (VALID_EMAIL_ADDRESS_REGEX.matcher(form.getEmailAddress()).matches() == false) {
				errors.rejectValue("emailAddress", "emailAddress.notValid");
			}
		}
		if (form.getMobileNo() != null && form.getMobileNo().trim().length() > 0) {
			if (form.getMobileNo().trim().length() != 10) {
				errors.rejectValue("mobileNo", "mobileNo.notValid");
			}
		}
	}

}
