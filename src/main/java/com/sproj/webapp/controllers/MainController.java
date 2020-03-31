package com.sproj.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value = {  "/resource" })
	public String resource(Model model) {
		model.addAttribute("msg", "test");
		return "resource";
	}

}
