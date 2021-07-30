package com.acheter.accountservice.service.store;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.acheter.accountservice.dto.store.ServiceAreaDto;
import com.acheter.accountservice.repositories.ServiceAreaRepository;

@Service
public class StoreService {
	@Autowired
	private ServiceAreaRepository serviceAreaRepository;

	public List<ServiceAreaDto> getServiceAreas(String city) {
		return serviceAreaRepository.findByCity(city).stream().map((e) -> {
			ServiceAreaDto dto = new ServiceAreaDto();

			dto.setServiceAreaId(e.getServiceAreaId());
			dto.setAreaName(e.getAreaName());

			return dto;
		}).collect(Collectors.toList());
	}

	public List<String> getCities() {
		return serviceAreaRepository.findAllCities(Sort.by(Order.asc("city")));
	}

	public List<String> getStates() {
		return serviceAreaRepository.findAllStates(Sort.by(Order.asc("state")));
	}

}
