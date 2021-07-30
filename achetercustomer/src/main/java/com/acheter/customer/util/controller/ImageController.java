package com.acheter.customer.util.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acheter.customer.dto.adstrading.ImageDto;
import com.acheter.customer.service.adstrading.AdsTradingService;

@RestController
@RequestMapping("/image")
public class ImageController {
	@Autowired
	private AdsTradingService adsTradingService;

	@GetMapping(value = "/{imageId}", produces = { MediaType.IMAGE_PNG_VALUE })
	public byte[] getImage(@PathVariable("imageId") long imageId) {
		ImageDto imageDto = null;

		imageDto = adsTradingService.getImage(imageId);
		return imageDto.getImageFileContent();
	}
}
