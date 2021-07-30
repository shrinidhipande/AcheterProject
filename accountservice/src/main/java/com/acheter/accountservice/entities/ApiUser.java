package com.acheter.accountservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name = "api_users")
@Entity
@Setter
@Getter
public class ApiUser implements Serializable {
	private static final long serialVersionUID = 3078000892318283432L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "api_user_id")
	protected int apiUserId;
	@Column(name = "api_key")
	protected String apiKey;
	protected String secret;
	protected String status;

	@ManyToOne
	@JoinColumn(name = "user_role_id")
	protected UserRole apiUserRole;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;
}
