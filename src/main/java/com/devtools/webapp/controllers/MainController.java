package com.devtools.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devtools.webapp.DataHandler;
import com.devtools.webapp.Resource;

/**
 * @author carl
 *
 * Main Controller - handles URL Mapping and adding/deleting objects.
 * 
 */
@Controller
public class MainController {

	/**
	 * RequestMapping for "/" and "/index" URL.
	 * 
	 * @param model
	 * @return "index" (templates/index.html)
	 */
	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		return "index";
	}

	/**
	 * RequestMapping for /tools URL.
	 * 
	 * Adding attributes "resource", "allTags", "allResources" to render data
	 * and "dataHandler" to create new Resource.
	 * 
	 * @param model
	 * @return "tools" (/templates/tools.html)
	 */
	@RequestMapping(value = "/tools")
	public String tools(Model model) {
		model.addAttribute("resource", new Resource());
		model.addAttribute("allTags", DataHandler.fetchTags(DataHandler.JSON_DATA));
		model.addAttribute("allResources", DataHandler.fetchJsonData(DataHandler.JSON_DATA));
		model.addAttribute("dataHandler", new DataHandler());
		return "tools";
	}

	/**
	 * RequestMapping for /tags/{tag} URL.
	 * 
	 * Adding attributes "allTags", "tag", "allTagResources" to render tag data.
	 * 
	 * @param tag - fetches data containing this tag
	 * @param model
	 * @return "tag" (/templates/tag.html)
	 */
	@RequestMapping(value = "/tags/{tag}")
	public String tags(@PathVariable("tag") String tag, Model model) {
		model.addAttribute("allTags", DataHandler.fetchTags(DataHandler.JSON_DATA));
		model.addAttribute("tag", tag);
		model.addAttribute("allTagResources", DataHandler.fetchTagData(tag, DataHandler.JSON_DATA));
		return "tag";
	}


	/**
	 * RequestMapping for /delete/{id} URL.
	 * 
	 * @param id - delete Resource with this id
	 * @param model
	 * @return "rederict:../tools" (Redirecting back to tools, this is to avoid rendering error)
	 */
	@RequestMapping(value = "/delete/{id}")
	public String deleteByID(@PathVariable("id") String id, Model model) {
		int intID = Integer.parseInt(id);
		DataHandler.deleteResource(intID, DataHandler.JSON_DATA);
		return "redirect:../tools";
	}

	/**
	 * RostMapping for /tools URL.
	 * 
	 * Fetches data from input form on page, if all fields are not empty/blank
	 * it creates a new Resource with addResource() method and sends you to /success_add URL.
	 * 
	 * If fields are empty/blank it will not add the Resource and send you back to /tools.
	 * 
	 * @param model
	 * @return "success_add" if data is filled out correctly, otherwise "tools"
	 */
	@PostMapping(value = "/tools")
	public String addTool(@ModelAttribute Resource resource) {
		if (!resource.getName().isBlank() && !resource.getWebsite().isBlank() && !resource.getDescription().isBlank()
				&& !resource.getImageLink().isBlank() && !resource.getTags().isEmpty()) {
			DataHandler.addResource(resource, DataHandler.JSON_DATA);
			return "success_add";
		} else {
			return "tools";
		}
	}

	/**
	 * RequestMapping for /contact URL.
	 * 
	 * @param model
	 * @return "contact" (/templates/contact.html)
	 */
	@RequestMapping(value = "/contact")
	public String editor(Model model) {
		return "contact";
	}
}