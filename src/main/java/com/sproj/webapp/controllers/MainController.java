package com.sproj.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/index" })
	public String homepage() {
		return "index";
	}

}
