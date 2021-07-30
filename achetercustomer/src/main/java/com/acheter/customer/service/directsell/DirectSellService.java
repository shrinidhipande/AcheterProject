package com.acheter.customer.service.directsell;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.acheter.customer.dto.post.DirectSellPostDto;
import com.acheter.customer.feign.configuration.AdsTradingServiceClientConfiguration;

@FeignClient(name = "directSellService", url = "${adsTradingService.url}/directsell", configuration = {
		AdsTradingServiceClientConfiguration.class })
public interface DirectSellService {
	@GetMapping(value = "/{status}/{size}/popular", produces = { MediaType.APPLICATION_JSON_VALUE })
	List<DirectSellPostDto> getPopularDirectSell(@PathVariable("status") String status,
			@PathVariable("size") int size);
}
