package com.acheter.customer.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AcheterHomeController {

	@RequestMapping("/acheter-home")
	public String showHomePage() {
		return "acheter-home";
	}

}
