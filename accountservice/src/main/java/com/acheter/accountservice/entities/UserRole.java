package com.acheter.accountservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 6142723812566646007L;
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long roleId;
	@Column(name = "role_nm")
	protected String roleName;
	protected String description;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;

}