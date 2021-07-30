package com.acheter.customer.dto.post;

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
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
}
