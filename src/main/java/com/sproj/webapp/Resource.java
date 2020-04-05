package com.sproj.webapp;

import java.util.ArrayList;

public class Resource{
	
	private String name;
	private String website;
	private String comment;
	private ArrayList<String> tags;
	
	public Resource() {};
	/**
	 * Constructor with 1 parameters
	 * @param name (name of resource - example: "github")
	 */
	public Resource(String name){
		this.name = name;
	}

	/**
	 * Constructor with 2 parameters
	 * @param name (name of resource - example: "github")
	 * @param website (website address - example: "www.github.com)
	 */
	public Resource(String name, String website) {
		this.name = name;
		this.website = website;
	}
	
	/**
	 * Constructor with 3 parameters
	 * @param name (name of resource - example: "github")
	 * @param website (website adress - example: "www.github.com)
	 * @param description (describe website - example: "github, inc. is a us-based global company that provides hosting for software development version control using git.")
	 */
	public Resource(String name, String website, String comment) {
		this.name = name;
		this.website = website;
		this.comment = comment;

	}

	/**
	 * Constructor with 4 parameters
	 * @param name (name of resource - example: "github")
	 * @param website (website address - example: "www.github.com)
	 * @param description (describe website - example: "github, inc. is a us-based global company that provides hosting for software development version control using git.")
	 * @param tags (provides tags - example: "git, version control")
	 */
	public Resource(String name, String website, String comment, ArrayList<String> tags) {
		this.name = name;
		this.website = website;
		this.tags = tags;
		this.comment = comment;
	}


	/**
	 * Getter for name
	 * @return String with name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * @param String name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for website
	 * @return String website
	 */
	public String getWebsite() {
		if (website.startsWith("https://")) {
			return website;
		} else {
			return "https://" + website;
		}
	}

	/**
	 * Setter for website
	 * @param String website 
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Getter for description
	 * @return String description
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Setter for description
	 * @param String description
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Getter for tags
	 * @return String tags
	 */
	public ArrayList<String> getTags() {
		return new ArrayList<String>(tags);
		}

	/**
	 * Setter for tags
	 * @param String tags
	 */
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	/**
	 * Overridden toString() method
	 * Returns all class variables as a string 
	 */
	@Override
	public String toString() {
	    return "Resource [name=" + getName() + ", website=" + getWebsite() + ", comment=" + getComment() + ", tags=" + getTags() + "]";
	  }
}

