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

import lombok.Data;

@Data
@Entity
@Table(name = "system_users")
public class SystemUser implements Serializable {
	private static final long serialVersionUID = -5716217221501186668L;
	@Id
	@Column(name = "system_user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long systemUserId;
	@Column(name = "email_address")
	protected String emailAddress;
	@Column(name = "mobile_no")
	protected String mobileNo;
	@Column(name = "first_nm")
	protected String firstName;
	@Column(name = "last_nm")
	protected String lastName;
	protected String password;
	@Column(name = "display_nm")
	protected String displayName;
	@Column(name = "email_verification_code")
	protected String emailVerificationCode;
	@Column(name = "email_verification_code_generated_dt")
	protected Date emailVerificationCodeGeneratedDate;
	@Column(name = "otp_code")
	protected String otpCode;
	@Column(name = "otp_code_generated_dt")
	protected Date otpCodeGeneratedDate;
	protected String status;
	@Column(name = "created_dt")
	protected Date createdDate;
	@Column(name = "created_by")
	protected String createdBy;
	@Column(name = "last_modified_dt")
	protected Date lastModifiedDate;
	@Column(name = "last_modified_by")
	protected String lastModifiedBy;

	@ManyToOne
	@JoinColumn(name = "user_role_id")
	protected UserRole userRole;
}
