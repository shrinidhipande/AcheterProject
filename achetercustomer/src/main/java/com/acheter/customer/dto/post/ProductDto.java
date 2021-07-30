package com.acheter.customer.dto.post;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {
	private long productNo;
	private String productName;
	private String description;
	private String model;
	private String manufacturer;
	private double price;
	private String categoryName;
	List<Long> productImages;

}
