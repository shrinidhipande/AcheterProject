package com.acheter.customer.dto.post;

import java.util.Date;
import java.util.List;

import com.acheter.customer.dto.account.ServiceAreaDto;
import com.acheter.customer.dto.adstrading.CategoryDto;
import com.acheter.customer.dto.adstrading.ImageDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDto {
	private long postId;
	private String title;
	private String description;
	private CategoryDto category;
	private String postedBy;
	private Date postedDate;
	private double price;
	private List<ImageDto> postImages;
	private ServiceAreaDto serviceArea;
	private AddressDto address;
	private ContactDetailsDto contactDetails;
	private String status;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
}
