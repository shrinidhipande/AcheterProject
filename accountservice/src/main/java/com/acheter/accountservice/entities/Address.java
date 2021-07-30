package com.acheter.accountservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "address")
public class Address implements Serializable {
	private static final long serialVersionUID = 9207471914115037320L;
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long addressId;
	@Column(name = "address_line1")
	protected String addressLine1;
	@Column(name = "address_line2")
	protected String addressLine2;
	protected String city;
	protected String state;
	protected int zip;
	protected String country;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;

}
