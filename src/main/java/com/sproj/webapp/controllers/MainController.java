package com.sproj.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sproj.webapp.CSVHandler;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		model.addAttribute("allResources", CSVHandler.fetchData());
		return "index";
	}

	@RequestMapping(value = { "/resources" })
	public String resources(Model model) {
		return "resources";
	}

	@RequestMapping(value = { "/tags" })
	public String tags(Model model) {
		return "tags";
	}

	@RequestMapping(value = { "/contact" })
	public String contact(Model model) {
		return "contact";
	}
}
