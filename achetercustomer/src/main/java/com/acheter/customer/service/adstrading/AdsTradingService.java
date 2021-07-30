package com.acheter.customer.service.adstrading;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.acheter.customer.dto.adstrading.CategoryDto;
import com.acheter.customer.dto.adstrading.ImageDto;
import com.acheter.customer.feign.configuration.AdsTradingServiceClientConfiguration;

@FeignClient(name = "AdsTradingService", url = "${adsTradingService.url}/adsTrading/", configuration = {
		AdsTradingServiceClientConfiguration.class })
public interface AdsTradingService {
	@GetMapping(value = "/parent/{size}/categories", produces = { MediaType.APPLICATION_JSON_VALUE })
	List<CategoryDto> getParentCategories(@PathVariable("size") int size);

	@GetMapping(value = "/{imageId}/image", produces = { MediaType.APPLICATION_JSON_VALUE })
	ImageDto getImage(@PathVariable("imageId") long imageId);

	@GetMapping(value = "/categories", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<CategoryDto> getCategories();
}
