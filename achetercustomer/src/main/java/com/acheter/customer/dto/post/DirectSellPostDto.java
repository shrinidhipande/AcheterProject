package com.acheter.customer.dto.post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DirectSellPostDto extends PostDto {
	private String productName;
	private String model;
	private String manufacturer;
}
