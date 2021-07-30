package com.acheter.accountservice.service.account;

import static com.acheter.accountservice.utils.AccountServiceConstants.ROLE_CUSTOMER;
import static com.acheter.accountservice.utils.AccountServiceConstants.STATUS_USER_NEW;
import static com.acheter.accountservice.utils.AccountServiceConstants.SYSTEM_USER;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acheter.accountservice.dto.account.AccountVerificationStatusDto;
import com.acheter.accountservice.dto.account.ForgotPasswordDto;
import com.acheter.accountservice.dto.account.UserAccountDto;
import com.acheter.accountservice.entities.SystemUser;
import com.acheter.accountservice.entities.UserRole;
import com.acheter.accountservice.exception.AcheterError;
import com.acheter.accountservice.exception.UserAccountNotFoundException;
import com.acheter.accountservice.exception.VerificationCodeMismatchException;
import com.acheter.accountservice.mapper.account.AccountMapper;
import com.acheter.accountservice.repository.SystemUserRepository;
import com.acheter.accountservice.repository.UserRoleRepository;
import com.acheter.accountservice.utils.AccountServiceConstants;
import com.acheter.accountservice.utils.RandomGenerator;

@Service
public class AccountService {
	private final static Logger logger = LoggerFactory.getLogger(AccountService.class);
	@Autowired
	private MessageSource messageSource;
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

	
	@Transactional(readOnly = false)
	public long verifyUserDetailsAndRegenerateCode(ForgotPasswordDto forgotPasswordDto)
			throws UserAccountNotFoundException {
		long systemUserId = 0;
		AcheterError error = null;
		SystemUser systemUser = null;
		String emailVerificationCode = null;
		String otpCode = null;
		logger.trace("entered into verifyUserDetailsAndRegenerateCode()");
		if (forgotPasswordDto.getEmailAddress() != null && forgotPasswordDto.getEmailAddress().trim().length() > 0) {
			logger.debug("finding SystemUser with emailAddress {} for resetting codes",
					forgotPasswordDto.getEmailAddress());
			systemUser = systemUserRepository.findByEmailAddress(forgotPasswordDto.getEmailAddress());
		} else {
			systemUser = systemUserRepository.findByMobileNo(forgotPasswordDto.getMobileNo());
			logger.debug("finding SystemUser with mobileNo {} for resetting codes", forgotPasswordDto.getMobileNo());
		}
		if (systemUser == null || systemUser.getStatus().equals(AccountServiceConstants.STATUS_USER_ACTIVE) == false) {
			logger.warn("unable to find SystemUser for emailAddress {} or mobileNo: {} so reporting exception",
					forgotPasswordDto.getEmailAddress(), forgotPasswordDto.getMobileNo());

			error = new AcheterError(AccountServiceConstants.ERR_CODE_USER_NOT_FOUND, messageSource
					.getMessage(AccountServiceConstants.ERR_CODE_USER_NOT_FOUND_KEY, null, Locale.getDefault()));
			throw new UserAccountNotFoundException("user account not available",
					Arrays.asList(new AcheterError[] { error }));
		}

		systemUserId = systemUser.getSystemUserId();
		logger.debug("found system user with id : {} for regenerating the codes", systemUserId);

		emailVerificationCode = RandomGenerator.generateVerificationCode(12);
		otpCode = RandomGenerator.generateOtp(4);
		int c = systemUserRepository.updateVerificationCode(emailVerificationCode, new Date(), otpCode, new Date(),
				systemUserId);
		logger.debug("regenerate code completed and no of records updated are {} ", c);

		return systemUserId;
	}
	
	@Transactional(readOnly = true)
	public boolean matchSystemUserEmailVerificationCode(long systemUserId, String emailVerificationCode) {
		return systemUserRepository.countBySystemUserIdAndEmailVerificationCode(systemUserId,
				emailVerificationCode) == 1 ? true : false;
	}
	
	@Transactional(readOnly = true)
	public UserAccountDto getUserAccount(String emailAddress) throws UserAccountNotFoundException {
		UserAccountDto userAccountDto = null;
		SystemUser systemUser = null;
		AcheterError error = null;

		systemUser = systemUserRepository.findByEmailAddress(emailAddress);
		if (systemUser == null) {
			error = new AcheterError(AccountServiceConstants.ERR_CODE_USER_NOT_FOUND, messageSource
					.getMessage(AccountServiceConstants.ERR_CODE_USER_NOT_FOUND_KEY, null, Locale.getDefault()));
			throw new UserAccountNotFoundException("user account with email address: " + emailAddress + " not found",
					error);
		}
		userAccountDto = AccountMapper.mapSystemUserToUserAccountDto(systemUser);

		return userAccountDto;
	}
	
	@Transactional(readOnly = true)
	public UserAccountDto getUserAccount(long userAccountNo) throws UserAccountNotFoundException {
		AcheterError error = null;
		UserAccountDto userAccountDto = null;
		Optional<SystemUser> systemUser = null;

		systemUser = systemUserRepository.findById(userAccountNo);
		if (systemUser.isPresent()) {
			userAccountDto = AccountMapper.mapSystemUserToUserAccountDto(systemUser.get());
		} else {
			error = new AcheterError(AccountServiceConstants.ERR_CODE_USER_NOT_FOUND, messageSource
					.getMessage(AccountServiceConstants.ERR_CODE_USER_NOT_FOUND_KEY, null, Locale.getDefault()));
			throw new UserAccountNotFoundException("user account with userAccountNo : " + userAccountNo + " not found",
					error);
		}

		return userAccountDto;
	}
	@Transactional(readOnly = false)
	public AccountVerificationStatusDto verifyEmailAddressAndActivateAccount(long userAccountNo,
			String emailActiviationCode) throws VerificationCodeMismatchException {
		AccountVerificationStatusDto accountVerificationStatusDto = null;
		SystemUser systemUser = null;
		AcheterError error = null;
		int emailVerificationStatus = 0;
		int otpCodeStatus = 0;
		String accountStatus = null;

		systemUser = systemUserRepository.getById(userAccountNo);

		if (systemUser.getEmailVerificationCode().equals(emailActiviationCode) == false) {
			error = new AcheterError(AccountServiceConstants.ERR_CODE_EMAIL_VERIFICATION_FAILED,
					messageSource.getMessage(AccountServiceConstants.ERR_CODE_EMAIL_VERIFICATION_FAILED_KEY, null,
							Locale.getDefault()));
			throw new VerificationCodeMismatchException("email verification code failed", error);
		}
		emailVerificationStatus = 1;
		otpCodeStatus = systemUser.getOtpCodeStatus();
		if (otpCodeStatus == 1) {
			accountStatus = AccountServiceConstants.STATUS_USER_ACTIVE;
		} else {
			accountStatus = systemUser.getStatus();
		}
		systemUserRepository.updateSystemUserVerificationStatus(userAccountNo, emailVerificationStatus, otpCodeStatus,
				accountStatus);
		accountVerificationStatusDto = new AccountVerificationStatusDto();
		accountVerificationStatusDto.setUserAccountNo(userAccountNo);
		accountVerificationStatusDto.setEmailVerificationStatus(emailVerificationStatus);
		accountVerificationStatusDto.setOtpCodeStatus(otpCodeStatus);

		return accountVerificationStatusDto;
	}

	@Transactional(readOnly = false)
	public AccountVerificationStatusDto verifyOtpCodeAndActivateAccount(long userAccountNo, String otpCode)
			throws VerificationCodeMismatchException {
		AccountVerificationStatusDto accountVerificationStatusDto = null;
		SystemUser systemUser = null;
		AcheterError error = null;
		int emailVerificationStatus = 0;
		int otpCodeStatus = 0;
		String accountStatus = null;

		systemUser = systemUserRepository.getById(userAccountNo);

		if (systemUser.getOtpCode().equals(otpCode) == false) {
			error = new AcheterError(AccountServiceConstants.ERR_CODE_OTP_CODE_FAILED, messageSource
					.getMessage(AccountServiceConstants.ERR_CODE_OTP_CODE_FAILED_KEY, null, Locale.getDefault()));
			throw new VerificationCodeMismatchException("otp code failed", error);
		}
		otpCodeStatus = 1;
		emailVerificationStatus = systemUser.getEmailVerificationStatus();
		if (emailVerificationStatus == 1) {
			accountStatus = AccountServiceConstants.STATUS_USER_ACTIVE;
		} else {
			accountStatus = systemUser.getStatus();
		}
		systemUserRepository.updateSystemUserVerificationStatus(userAccountNo, emailVerificationStatus, otpCodeStatus,
				accountStatus);
		accountVerificationStatusDto = new AccountVerificationStatusDto();
		accountVerificationStatusDto.setUserAccountNo(userAccountNo);
		accountVerificationStatusDto.setEmailVerificationStatus(emailVerificationStatus);
		accountVerificationStatusDto.setOtpCodeStatus(otpCodeStatus);

		return accountVerificationStatusDto;
	}

	

	@Transactional(readOnly = false)
	public int updatePassword(long userAccountNo, String password) {
		return systemUserRepository.updatePassword(userAccountNo, password);
	}
	
	@Transactional(readOnly = true)
	public boolean matchSystemUserOtpCode(long systemUserId, String otpCode) {
		return systemUserRepository.countBySystemUserIdAndOtpCode(systemUserId, otpCode) == 1 ? true : false;
	}
	
	
}
