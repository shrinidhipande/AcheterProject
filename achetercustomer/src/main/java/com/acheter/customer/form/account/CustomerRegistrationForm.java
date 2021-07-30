package com.acheter.customer.form.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CustomerRegistrationForm {
	@Email
	@NotBlank
	private String emailAddress;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Length(min = 8, max = 12)
	private String displayName;
	@NotBlank
	private String mobileNo;
	@Length(min = 8)
	private String password;
	@NotBlank
	private String reTypePassword;
	@NotNull
	private Integer termsAndConditions;

}
