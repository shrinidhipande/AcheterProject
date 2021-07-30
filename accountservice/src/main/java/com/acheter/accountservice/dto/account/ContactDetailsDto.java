package com.acheter.accountservice.dto.account;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactDetailsDto {
	private int contactDetailsId;
	private String primaryContactNo;
	private String secondaryContactNo;
	private String primaryEmailAddress;
	private String secondaryEmailAddress;
	private Date createdDate;
	private String lastModifiedBy;
	private String lastModifiedDate;
}
