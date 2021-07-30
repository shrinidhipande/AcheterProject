package com.acheter.accountservice.dto.account;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
	private long addressId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private long zip;
	private String country;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private String lastModifiedDate;

}
