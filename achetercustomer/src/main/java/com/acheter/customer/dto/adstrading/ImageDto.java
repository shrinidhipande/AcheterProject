package com.acheter.customer.dto.adstrading;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageDto {
	private long imageId;
	private String fileName;
	private byte[] imageFileContent;
	private String contentType;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;

}
