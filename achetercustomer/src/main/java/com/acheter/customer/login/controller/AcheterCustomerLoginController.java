package com.acheter.customer.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class AcheterCustomerLoginController {
	private final String VIEW_LOGIN_PAGE = "login";
	private final String VIEW_LOGOUT_PAGE = "logout";

	@GetMapping("/login")
	public String showLoginPage() {
		return VIEW_LOGIN_PAGE;
	}

	@GetMapping("/logout")
	public String showLogoutPage() {
		return VIEW_LOGOUT_PAGE;
	}

}
