package com.devtools.webapp;

import java.util.ArrayList;

/**
 * A class that holds data about development tools.
 * 
 * @author carl
 *
 */
public class Resource {

	static int count = 1001;
	private int id;
	private String name;
	private String website;
	private String description;
	private String imageLink;
	private ArrayList<String> tags;

	/**
	 * Empty constructor.
	 * Automatically adds incremented id.
	 */
	public Resource() {
		this.id = count;
		count++;
	}

	/**
	 * Constructor with 1 parameter.
	 * 
	 * @param name         (name of resource - example: "github")
	 */
	public Resource(String name) {
		this.name = name;
		this.id = count;
	}

	/**
	 * Constructor with 2 parameters.
	 * 
	 * @param name         (name of resource - example: "github")
	 * @param website      (website address - example: "www.github.com)
	 */
	public Resource(String name, String website) {
		this.name = name;
		this.website = website;
		this.id = count;
	}

	/**
	 * Constructor with 3 parameters.
	 * 
	 * @param name         (name of resource - example: "github")
	 * @param website      (website address - example: "www.github.com)
	 * @param description  (short description- example: "github, inc. is a us-based global company that 
							provides hosting for software development version control using git.")
	 */
	public Resource(String name, String website, String description) {
		this.name = name;
		this.website = website;
		this.description = description;
		this.id = count;
	}

	/**
	 * Constructor with 4 parameters.
	 * 
	 * @param name         (name of resource - example: "github")
	 * @param website      (website address - example: "www.github.com)
	 * @param description  (short description- example: "github, inc. is a us-based global company that 
							provides hosting for software development version control using git.")
	 * @param setImageLink (image adress - example: "www.photowebsite.com/image.jpg")
	 */
	public Resource(String name, String website, String description, String imageLink) {
		this.name = name;
		this.website = website;
		this.description = description;
		this.setImageLink(imageLink);
		this.id = count;
	}

	/**
	 * Constructor with 5 parameters.
	 * 
	 * @param name         (name of resource - example: "github")
	 * @param website      (website address - example: "www.github.com)
	 * @param description  (short description- example: "github, inc. is a us-based global company that 
							provides hosting for software development version control using git.")
	 * @param setImageLink (image adress - example: "www.photowebsite.com/image.jpg"))
	 * @param tags         (provides tags - example: "git, version control")
	 */
	public Resource(String name, String website, String description, String imageLink, ArrayList<String> tags) {
		this.name = name;
		this.website = website;
		this.tags = tags;
		this.setImageLink(imageLink);
		this.description = description;
		this.id = count;
	}

	/**
	 * Getter for id
	 * 
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter for name.
	 * 
	 * @return String with name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name.
	 * 
	 * @param String name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for website.
	 * 
	 * @return String website (If website starts with "https://" return the string, else
	 *         return "https://" + String website).
	 */
	public String getWebsite() {
		if (website.startsWith("http")) {
			return website;
		} else {
			return "https://" + website;
		}
	}

	/**
	 * Setter for website.
	 * 
	 * @param String website.
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * Getter for description.
	 * 
	 * @return String description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for description.
	 * 
	 * @param String description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for getImageLink.
	 * 
	 * @return String imageLink (If imageLink starts with "https://" return the string,
	 *         else return "https://" + String imageLink).
	 */
	public String getImageLink() {
		if (imageLink.startsWith("http")) {
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
	 * 
	 * @return ArrayList<String> with tags.
	 */
	public ArrayList<String> getTags() {
		return new ArrayList<String>(tags);
	}

	/**
	 * Setter for tags.
	 * 
	 * @param ArrayList<tags> tags.
	 */
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	/**
	 * Overridden toString() method. Returns all class variables as a formatted string.
	 */
	@Override
	public String toString() {
		return "Resource [name=" + getName() + ", website=" + getWebsite() + ", description=" + getDescription()
				+ ", imagelink=" + getImageLink() + ", tags=" + getTags() + "]";
	}
}
