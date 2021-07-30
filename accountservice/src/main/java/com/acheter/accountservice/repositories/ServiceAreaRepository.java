package com.acheter.accountservice.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acheter.accountservice.entities.ServiceArea;

public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long> {
	List<ServiceArea> findByCity(String city);

	@Query("select distinct sa.city from ServiceArea sa")
	List<String> findAllCities(Sort sort);

	@Query("select distinct sa.state from ServiceArea sa")
	List<String> findAllStates(Sort sort);

}
