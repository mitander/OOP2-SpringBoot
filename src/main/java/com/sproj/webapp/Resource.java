package com.sproj.webapp;

public class Resource{
	
	private String name;
	private String website;
	private String description;
	private String tags;
	private String comments;
	
	/**
	 * empty constructor 
	 */
	public Resource(){
		
	}
	
	/**
	 * 1 parameter constructor (String)
	 * @param name
	 */
	public Resource(String name){

		this.setName(name);
	}
	
	/**
	 * 2 parameter constructor (String, String)
	 * @param name
	 * @param adress
	 */
	public Resource(String name, String adress) {

		this.setName(name);
		this.setWebsite(adress);

	}
	
	/**
	 * 3 parameter constructor (String, String, String)
	 * @param name
	 * @param website
	 * @param description
	 */
	public Resource(String name, String website, String description) {

		this.setName(name);
		this.setWebsite(website);
		this.setDescription(description);

	}

	/**
	 * Constructor with 4 parameters
	 * @param name (Name of resource - example: "Github")
	 * @param website (Website adress - example: "www.github.com)
	 * @param description (Describe website - example: "GitHub, Inc. is a US-based global company that provides hosting for software development version control using Git.")
	 * @param tags (Provides tags - example: "Git, Version Control")
	 */
	public Resource(String name, String website, String description, String tags) {

		this.setName(name);
		this.setWebsite(website);
		this.setDescription(description);
		this.setTags(tags);

	}

	/**
	 * 5 parameter constructor (String, String, String, String, String)
	 * @param name
	 * @param website
	 * @param description
	 * @param tags
	 * @param comments
	 */
	public Resource(String name, String website, String description, String tags, String comments) {

		this.setName(name);
		this.setWebsite(website);
		this.setDescription(description);
		this.setTags(tags);
		this.setComments(comments);

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

		return website;
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
	public String getDescription() {

		return description;
	}

	/**
	 * Setter for description
	 * @param String description
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * Getter for tags
	 * @return String tags
	 */
	public String getTags() {

		return tags;
	}

	/**
	 * Setter for tags
	 * @param String tags
	 */
	public void setTags(String tags) {

		this.tags = tags;
	}

	/**
	 * Getter for comments
	 * @return String comments
	 */
	public String getComments() {

		return comments;
	}

	/**
	 * Setter for comments
	 * @param String comments
	 */
	public void setComments(String comments) {

		this.comments = comments;
	}

	/**
	 * Overridden toString() method
	 * Returns all class variables as a string 
	 */
	@Override
	public String toString() {
		return "Resource [name=" + name + "website=" + website + ", comments=" + comments + ", description=" + description + ", tags=" + tags + "]";
	}
}

