package com.acheter.accountservice.mapper.account;

import com.acheter.accountservice.dto.account.UserAccountDto;
import com.acheter.accountservice.entities.SystemUser;

public class AccountMapper {
	public static UserAccountDto mapSystemUserToUserAccountDto(SystemUser systemUser) {
		UserAccountDto userAccountDto = null;

		if (systemUser != null) {
			userAccountDto = new UserAccountDto();
			userAccountDto.setUserAccountNo(systemUser.getSystemUserId());
			userAccountDto.setDisplayName(systemUser.getDisplayName());
			userAccountDto.setFirstName(systemUser.getFirstName());
			userAccountDto.setLastName(systemUser.getLastName());
			userAccountDto.setEmailAddress(systemUser.getEmailAddress());
			userAccountDto.setMobileNo(systemUser.getMobileNo());
			userAccountDto.setPassword(systemUser.getPassword());
			userAccountDto.setStatus(systemUser.getStatus());
			userAccountDto.setRoleName(systemUser.getUserRole().getRoleName());
		}

		return userAccountDto;
	}
}
