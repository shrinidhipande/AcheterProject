package com.acheter.customer.dto.post;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExchangeProductDto {
	private long exchangeProductId;
	private Date exchangeRequestPlacedDate;
	private ProductDto customerProduct;
	private long storeProductId;
	private String postedBy;
	private String serviceAreaName;

}
