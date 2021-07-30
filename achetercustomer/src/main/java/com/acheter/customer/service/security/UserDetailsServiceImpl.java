package com.acheter.customer.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acheter.customer.accountservice.exception.UserNotFoundException;
import com.acheter.customer.beans.security.UserDetailsImpl;
import com.acheter.customer.dto.account.UserAccountDto;
import com.acheter.customer.service.account.AccountService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccountDto userAccount = null;
		UserDetails userDetails = null;

		try {
			logger.debug("making an api call to get userAccount details for emailAddress: {}", username);
			userAccount = accountService.getUserAccount(username);
			logger.debug("user account {} ", userAccount);
		} catch (UserNotFoundException e) {
			logger.error("user account not found with emailAddress :{} ", username);
			throw new UsernameNotFoundException("username not found", e);
		}

		logger.debug("found user account with email address: {}", username);
		userDetails = new UserDetailsImpl(userAccount.getUserAccountNo(), userAccount.getEmailAddress(),
				userAccount.getMobileNo(), userAccount.getPassword(), userAccount.getRoleName(),
				userAccount.getStatus());

		return userDetails;
	}

}
