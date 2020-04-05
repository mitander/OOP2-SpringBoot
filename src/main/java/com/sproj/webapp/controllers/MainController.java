package com.sproj.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sproj.webapp.JsonDataHandler;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		model.addAttribute("allResources", JsonDataHandler.readData());
		return "index";
	}

	@RequestMapping(value = { "/resources" })
	public String resources(Model model) {
		return "resources";
	}
	
	@RequestMapping(value = { "/tags" })
	public String tags(Model model) {
		model.addAttribute("allTags", JsonDataHandler.fetchTags());
		return "tags";
	}

	@RequestMapping(value = "/tags/{tag}")
	public String tags(@PathVariable("tag") String tag, Model model) {
		model.addAttribute("allTagResources", JsonDataHandler.fetchTagData(tag));
		model.addAttribute("tag", tag);
		return "tag";
	}
	
	@RequestMapping(value = "/resources/{resource}")
	public String resource(@PathVariable("resource") String resource, Model model) {
		model.addAttribute("resource", JsonDataHandler.fetchResource(resource));
		return "resource";
	}
	

	@RequestMapping(value = { "/contact" })
	public String contact(Model model) {
		return "contact";
	}
}
