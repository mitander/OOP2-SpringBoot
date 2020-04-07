package com.sproj.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sproj.webapp.DataHandler;
import com.sproj.webapp.Resource;

/**
 * @author carl
 *
 */
@Controller
public class MainController {

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		return "index";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tools")
	public String tools(Model model) {
		model.addAttribute("resource", new Resource());
		model.addAttribute("allTags", DataHandler.fetchTags());
		model.addAttribute("allResources", DataHandler.fetchData());
		model.addAttribute("dataHandler", new DataHandler());
		return "tools";
	}

	/**
	 * @param tag
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tags/{tag}")
	public String tags(@PathVariable("tag") String tag, Model model) {
		model.addAttribute("allTags", DataHandler.fetchTags());
		model.addAttribute("tag", tag);
		model.addAttribute("allTagResources", DataHandler.fetchTagData(tag)); 
		return "tag";
	}

	@RequestMapping(value = "/delete/{id}")
    public String deleteByID(@PathVariable("id") String id, Model model) {
		int intID = Integer.parseInt(id);
		DataHandler.deleteResource(intID);
		return "success_delete";
	}
	/**
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/tools")
	public String addTool(@ModelAttribute Resource resource) {
		if (!resource.getName().isBlank() && !resource.getWebsite().isBlank()
		&& !resource.getComment().isBlank() && !resource.getImageLink().isBlank()
		&& !resource.getTags().isEmpty()) {
			DataHandler.addResource(resource);
		}
		return "success_add";
	}
	
	@RequestMapping(value = "/contact")
	public String editor(Model model) {
		return "contact";
	}
}