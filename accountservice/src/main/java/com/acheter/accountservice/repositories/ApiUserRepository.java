package com.acheter.accountservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acheter.accountservice.entities.ApiUser;

public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {
	ApiUser findByApiKey(String apiKey);

}
