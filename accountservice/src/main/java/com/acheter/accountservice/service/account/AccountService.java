package com.acheter.accountservice.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acheter.accountservice.dto.account.UserAccountDto;
import com.acheter.accountservice.entities.SystemUser;
import com.acheter.accountservice.entities.UserRole;
import com.acheter.accountservice.repository.SystemUserRepository;
import com.acheter.accountservice.repository.UserRoleRepository;
import com.acheter.accountservice.utils.RandomGenerator;

import static com.acheter.accountservice.utils.AccountServiceConstants.*;

import java.util.Date;

@Service
public class AccountService {
	private final static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private SystemUserRepository systemUserRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Transactional(readOnly = false)
	public long registerCustomer(UserAccountDto userAccount) {
		String verificationCode = null;
		SystemUser systemUser = null;
		UserRole userRole = null;
		long systemUserId = 0;
		String otp = null;

		logger.trace("entered into registerCustomer with account {}", userAccount);

		logger.trace("fetching the userRole for rolename : {}", ROLE_CUSTOMER);
		userRole = userRoleRepository.findUserRoleByRoleName(ROLE_CUSTOMER);
		logger.info("user role id {} for rolename {}", userRole.getRoleId(), ROLE_CUSTOMER);

		logger.trace("generating verification code of 12 characters for user of email address {}",
				userAccount.getEmailAddress());
		verificationCode = RandomGenerator.generateVerificationCode(12);
		logger.debug("verification code generated {} for user of email address {} ", verificationCode,
				userAccount.getEmailAddress());

		logger.trace("generating otp code of 4 digits in length for user of email address {}",
				userAccount.getEmailAddress());
		otp = RandomGenerator.generateOtp(4);
		logger.debug("otp code generated {} for user of email address {} ", otp, userAccount.getEmailAddress());

		systemUser = new SystemUser();
		systemUser.setFirstName(userAccount.getFirstName());
		systemUser.setLastName(userAccount.getLastName());
		systemUser.setEmailAddress(userAccount.getEmailAddress());
		systemUser.setDisplayName(userAccount.getDisplayName());
		systemUser.setMobileNo(userAccount.getMobileNo());
		systemUser.setPassword(userAccount.getPassword());

		systemUser.setEmailVerificationCode(verificationCode);
		systemUser.setEmailVerificationCodeGeneratedDate(new Date());
		systemUser.setOtpCode(otp);
		systemUser.setOtpCodeGeneratedDate(new Date());

		systemUser.setStatus(STATUS_USER_NEW);

		systemUser.setCreatedBy(SYSTEM_USER);
		systemUser.setLastModifiedBy(SYSTEM_USER);
		systemUser.setCreatedDate(new Date());
		systemUser.setLastModifiedDate(new Date());
		systemUser.setUserRole(userRole);

		logger.trace("saving systemuser into the database");
		systemUser = systemUserRepository.save(systemUser);
		systemUserId = systemUser.getSystemUserId();

		logger.info("saved system user with id {} for email address {} ", systemUserId, userAccount.getEmailAddress());

		return systemUserId;
	}

	public long countAccountsByEmailAddress(String emailAddress) {
		long c = 0;
		logger.trace("entered into countAccountsByEmailAddress with email address {}", emailAddress);
		c = systemUserRepository.countByEmailAddress(emailAddress);
		logger.debug("{} accounts found for email address {}", c, emailAddress);
		return c;
	}

	public long countAccountsByMobileNo(String mobileNo) {
		long c = 0;
		logger.trace("entered into countAccountsByMobileNo with mobileNo {}", mobileNo);
		c = systemUserRepository.countByMobileNo(mobileNo);

		logger.debug("{} accounts found for mobile no {}", c, mobileNo);
		return c;
	}

	public long countAccountsByDisplayName(String displayName) {
		long c = 0;
		logger.trace("entered into countAccountsByDisplayName with displayName {}", displayName);
		c = systemUserRepository.countByDisplayName(displayName);

		logger.debug("{} accounts found for displayName {}", c, displayName);
		return c;
	}

}
