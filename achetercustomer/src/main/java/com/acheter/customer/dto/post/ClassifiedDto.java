package com.acheter.customer.dto.post;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClassifiedDto extends PostDto {
	private ClassifiedTypeDto classifiedTypeDto;
	private Date expiryDate;
	private long classifiedFee;
}
