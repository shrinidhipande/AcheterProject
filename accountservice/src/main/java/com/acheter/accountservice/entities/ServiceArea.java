package com.acheter.accountservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "service_area")
@Setter
@Getter
public class ServiceArea implements Serializable {
	private static final long serialVersionUID = 9054923535172656103L;
	@Id
	@Column(name = "service_area_id")
	protected long serviceAreaId;
	@Column(name = "area_nm")
	protected String areaName;
	protected String description;
	protected String city;
	protected String state;
	protected String pincode;
	protected String status;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;

}
