package com.sproj.webapp;

import java.util.ArrayList;

/**
 * A class that holds data about development tools.
 * 
 * @author carl
 *
 */
public class Resource{
	
	static int count;
	private int id;
	private String name;
	private String website;
	private String comment;
	private String imageLink;
	private ArrayList<String> tags;
	
	/**
	 * Empty constructor.
	 */
	public Resource() {
		this.id = generateID();
	}

	/**
	 * Constructor with 1 parameter.
	 * @param name (name of resource - example: "github")
	 * @param id 
	 */
	public Resource(String name){
		this.id = generateID();
		this.name = name;
	}

	/**
	 * Constructor with 2 parameters.
	 * @param name (name of resource - example: "github")
	 * @param website (website address - example: "www.github.com)
	 * @param id 
	 */
	public Resource(String name, String website) {
		this.id = generateID();
		this.name = name;
		this.website = website;
	}
	
	/**
	 * Constructor with 3 parameters.
	 * @param name (name of resource - example: "github")
	 * @param website (website adress - example: "www.github.com)
	 * @param comment (short description - example: "github, inc. is a us-based global company that provides hosting for software development version control using git.")
	 * @param id 
	 */
	public Resource(String name, String website, String comment) {
		this.id = generateID();
		this.name = name;
		this.website = website;
		this.comment = comment;
	}

	/**
	 * Constructor with 4 parameters.
	 * @param name (name of resource - example: "github")
	 * @param website (website address - example: "www.github.com)
	 * @param comment (short description- example: "github, inc. is a us-based global company that provides hosting for software development version control using git.")
	 * @param id 
	 * @param setImageLink (image adress - example: "www.photowebsite.com/image.jpg")
	 * @param tags (provides tags - example: "git, version control")
	 */
	public Resource(String name, String website, String comment, String imageLink) {
		this.id = generateID();
		this.name = name;
		this.website = website;
		this.comment = comment;
		this.setImageLink(imageLink);
	}

	/**
	 * Constructor with 5 parameters.
	 * @param name (name of resource - example: "github")
	 * @param website (website address - example: "www.github.com)
	 * @param comment (short description- example: "github, inc. is a us-based global company that provides hosting for software development version control using git.")
	 * @param setImageLink (image adress - example: "www.photowebsite.com/image.jpg")
	 * @param tags (provides tags - example: "git, version control")
	 * @param id 
	 */
	public Resource(String name, String website, String comment,String imageLink, ArrayList<String> tags) {
		this.id = generateID();
		this.name = name;
		this.website = website;
		this.tags = tags;
		this.setImageLink(imageLink);
		this.comment = comment;
	}

	/**
	 * Getter for name.
	 * @return String with name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name.
	 * @param String name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for website.
	 * @return String website (if it starts with "https://" return the string, else return "https://" + String website).
	 */
	public String getWebsite() {
		if (website.startsWith("https://")) {
			return website;
		} else {
			return "https://" + website;
		}
	}

	/**
	 * Setter for website.
	 * @param String website.
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Getter for comment.
	 * @return String description.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Setter for comment.
	 * @param String description.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Getter for getImageLink.
	 * @return String imageLink (if it starts with "https://" return the string, else return "https://" + String imageLink).
	 */
	public String getImageLink() {
		if (imageLink.startsWith("https://")) {
			return imageLink;
		} else {
			return "https://" + imageLink;
		}
		
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	/**
	 * Getter for tags.
	 * @return ArrayList<String> with tags.
	 */
	public ArrayList<String> getTags() {
		return new ArrayList<String>(tags);
		}

	/**
	 * Setter for tags.
	 * @param ArrayList<tags> tags.
	 */
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	public int generateID() {
		count++;
		return count;
	}

	/**
	 * Overridden toString() method.
	 * Returns all class variables as a string.
	 */
	@Override
	public String toString() {
	    return "Resource [name=" + getName() + ", name=" + getName() + ", website=" + getWebsite() + ", comment=" + getComment() + ", imagelink=" + getImageLink() + ", tags=" + getTags() + "]";
	  }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

