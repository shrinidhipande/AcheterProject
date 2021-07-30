package com.acheter.accountservice.api.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acheter.accountservice.dto.store.ServiceAreaDto;
import com.acheter.accountservice.service.store.StoreService;

@RestController
@RequestMapping("/store")
public class ManageStoreApiService {
	@Autowired
	private StoreService storeService;

	@GetMapping(value = "/{cityName}/serviceAreas", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ServiceAreaDto>> getServiceAreas(@PathVariable("cityName") String cityName) {
		return ResponseEntity.ok(storeService.getServiceAreas(cityName));
	}

	@GetMapping(value = "/cities", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<String> getCities() {
		return storeService.getCities();
	}

	@GetMapping(value = "/states", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<String> getStates() {
		return storeService.getStates();
	}

}
