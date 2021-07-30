package com.acheter.customer.service.store;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.acheter.customer.dto.account.ServiceAreaDto;
import com.acheter.customer.feign.configuration.FeignAccountServiceClientConfiguration;

@FeignClient(name = "storeService", url = "${accountService.url}/store", configuration = FeignAccountServiceClientConfiguration.class)
public interface StoreService {
	@GetMapping(value = "/{cityName}/serviceAreas", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ServiceAreaDto> getServiceAreas(@PathVariable("cityName") String cityName);

	@GetMapping(value = "/cities", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<String> getCities();

	@GetMapping(value = "/states", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<String> getStates();
}
