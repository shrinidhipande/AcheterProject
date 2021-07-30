package com.acheter.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acheter.accountservice.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	UserRole findUserRoleByRoleName(String roleName);
}