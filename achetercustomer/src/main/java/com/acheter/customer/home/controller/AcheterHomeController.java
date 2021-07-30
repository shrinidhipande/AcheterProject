package com.acheter.customer.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acheter.customer.dto.adstrading.CategoryDto;
import com.acheter.customer.dto.post.ClassifiedDto;
import com.acheter.customer.dto.post.DirectSellPostDto;
import com.acheter.customer.dto.post.ExchangeProductDto;
import com.acheter.customer.service.adstrading.AdsTradingService;
import com.acheter.customer.service.classifieds.ClassifiedService;
import com.acheter.customer.service.directsell.DirectSellService;
import com.acheter.customer.service.exchange.ExchangeProductService;

import static com.acheter.customer.util.AcheterCustomerConstants.*;

@Controller
public class AcheterHomeController {
	@Autowired
	private AdsTradingService adsTradingService;
	@Autowired
	private ClassifiedService classifiedService;
	@Autowired
	private DirectSellService directSellService;
	@Autowired
	private ExchangeProductService exchangeProductService;

	@RequestMapping("/acheter-home")
	public String showHomePage(Model model) {
		List<CategoryDto> topCategories = null;
		List<ClassifiedDto> recentClassifieds = null;
		List<DirectSellPostDto> popularDirectSellPosts = null;
		List<ExchangeProductDto> exchangeProducts = null;

		topCategories = adsTradingService.getParentCategories(6);
		recentClassifieds = classifiedService.getRecentClassifieds(6, 15, STATUS_POST_ACTIVE);
		popularDirectSellPosts = directSellService.getPopularDirectSell(STATUS_POST_ACTIVE, 3);
		exchangeProducts = exchangeProductService.getRecentExchangeProducts(STATUS_POST_ACTIVE, 15, 3);

		model.addAttribute("topCategories", topCategories);
		model.addAttribute("recentClassifieds", recentClassifieds);
		model.addAttribute("popularDirectSellPosts", popularDirectSellPosts);
		model.addAttribute("exchangeProducts", exchangeProducts);

		return "acheter-home";
	}

}
