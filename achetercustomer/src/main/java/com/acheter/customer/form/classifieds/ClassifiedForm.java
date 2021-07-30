package com.acheter.customer.form.classifieds;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClassifiedForm {
	@NotBlank
	private String title;
	@Length(min = 100, max = 5000)
	private String description;
	@Positive
	private long categoryId;
	@Positive
	private long classifiedTypeId;
	@Positive
	private double price;
	@Positive
	private long serviceAreaId;
	@NotBlank
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	@Min(value = 100000)
	@Max(value = 999999)
	private int zip;
	@NotBlank
	private String country;
	@NotBlank
	private String secondaryContactNo;
	@NotBlank
	private String secondaryEmailAddress;
	@NotNull
	private Integer termsAndConditions;

}
