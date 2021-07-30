package com.acheter.customer.service.classifieds;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.acheter.customer.dto.post.ClassifiedDto;
import com.acheter.customer.dto.post.ClassifiedTypeDto;
import com.acheter.customer.dto.post.NewPostStatusDto;
import com.acheter.customer.feign.configuration.AdsTradingServiceClientConfiguration;

@FeignClient(name = "classifiedService", url = "${adsTradingService.url}/classified", configuration = {
		AdsTradingServiceClientConfiguration.class })
public interface ClassifiedService {
	@GetMapping(value = "/{days}/{size}/{status}/recent", produces = { MediaType.APPLICATION_JSON_VALUE })
	List<ClassifiedDto> getRecentClassifieds(@PathVariable("size") int size, @PathVariable("days") int days,
			@PathVariable("status") String status);

	@GetMapping(value = "/classifiedTypes", produces = { MediaType.APPLICATION_JSON_VALUE })
	List<ClassifiedTypeDto> getClassifiedTypes();

	@PostMapping(value = "/new", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	NewPostStatusDto saveClassified(@RequestBody ClassifiedDto classifiedDto);

	@PutMapping(value = "/paymentUpdate")
	ResponseEntity<?> updatePaymentStatus(@RequestParam("paymentId") String paymentId,
			@RequestParam("orderId") String orderId, @RequestParam("postId") String postId,
			@RequestParam("status") String status);
}
