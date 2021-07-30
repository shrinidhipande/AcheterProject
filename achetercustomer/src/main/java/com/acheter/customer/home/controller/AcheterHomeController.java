package com.acheter.customer.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import static com.acheter.customer.util.AcheterCustomerConstants.*;

@Controller
public class AcheterHomeController {


	@RequestMapping("/acheter-home")
	public String showHomePage(Model model) {

		return "acheter-home";
	}

}
