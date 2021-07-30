package com.acheter.accountservice.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.acheter.accountservice.entities.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
	long countByEmailAddress(String emailAddress);

	long countByMobileNo(String mobileNo);

	long countByDisplayName(String displayName);

	SystemUser findByEmailAddress(String emailAddress);

	SystemUser findByMobileNo(String mobileNo);

	@Modifying
	@Query("update SystemUser su set emailVerificationCode=?1, emailVerificationCodeGeneratedDate=?2, otpCode=?3, otpCodeGeneratedDate=?4 where su.systemUserId = ?5")
	int updateVerificationCode(String emailVerificationCode, Date emailVerificationCodeGeneratedDate, String otpCode,
			Date otpCodeGeneratedDate, long systemUserId);

	long countBySystemUserIdAndEmailVerificationCode(long systemUserId, String emailVerificationCode);

	long countBySystemUserIdAndOtpCode(long systemUserId, String otpCode);

	@Modifying
	@Query("update SystemUser su set su.password=?2 where su.systemUserId=?1")
	int updatePassword(long systemUserId, String password);

	@Modifying
	@Query("update SystemUser su set su.emailVerificationStatus=?2, su.otpCodeStatus=?3, su.status=?4 where su.systemUserId=?1")
	int updateSystemUserVerificationStatus(long systemUserId, int emailVerificationCodeStatus,
			int otpVerificationCodeStatus, String status);
}
