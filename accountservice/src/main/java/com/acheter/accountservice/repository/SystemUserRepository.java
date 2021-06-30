package com.acheter.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acheter.accountservice.entities.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Integer>{
	long countByEmailAddress(String emailAddress);
	long countByMobileNo(String mobileNo);
	long countByDisplayName(String displayName);
}
