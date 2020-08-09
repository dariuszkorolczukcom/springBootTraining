package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/view-products")
	public String viewProducts() {
		return "view-products";
	}

	@RequestMapping(value = "/add-products")
	public String addProducts() {
		return "add-products";
	}

	@RequestMapping("/locale")
	public String locale() {
		return "locale";
	}
}