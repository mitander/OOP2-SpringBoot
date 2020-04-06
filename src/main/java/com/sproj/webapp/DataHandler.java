package com.sproj.webapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author carl
 * 
 * DataHandler handles writing and reading data to a .json file and fetching tags from resouces.
 */
public class DataHandler {

	/**
	 * DATA_DIRECTORY & DATA_DIRECTORY_JSON are path variables.
	 * DATA_DIRECTORY : src/main/resources/static/data
	 * DATA_DIRECTORY_JSON: src/main/resource/static/data/resourcedata/resourceData.json
	 */
	public final static String DATA_DIRECTORY = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator;
	public final static String DATA_DIRECTORY_JSON = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator+ "resourcedata" + File.separator + "resourceData.json";

	
	/**
	 * Method that takes a new Resource, adds it to an ArrayList<Resource> together with
	 * already existing resourses and saves it in a json file.
	 * @param resource ( Resource you want to add )
	 */
	public static void addResource(Resource resource){
		ArrayList<Resource> allResources = readData();

		try {
			allResources.add(resource);
			
			  // create ObjectMapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // Use mapper to write json file
		    mapper.writeValue(Paths.get(DATA_DIRECTORY_JSON).toFile(), allResources);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	/**
	 * Method for deleting a resource, iterates through existing resources and delete the
	 * index with corresponding id.
	 * @param id ( ID of resource you want to delete)
	 */
	public static void deleteResource(int id) {
		ArrayList<Resource> allResources = readData();
		ObjectMapper mapper = new ObjectMapper();

		try {
			for (Iterator<Resource> iterator = allResources.iterator(); iterator.hasNext(); ) {
				  Resource resource = iterator.next();
				  if(resource.getId() == id){
				    iterator.remove();
				    mapper.writeValue(Paths.get(DATA_DIRECTORY_JSON).toFile(), allResources);
				  }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Method that uses ObjectMapper from Jackson to convert a JSON array to a ArrayList with read data.
	 * @return an ArrayList<Resource> with read items.
	 */
	public static ArrayList<Resource> readData() {
		ArrayList<Resource> resources = new ArrayList<Resource>();

		try {
		    // Create object mapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // Convert JSON array to ArrayList of resources.
		    resources = (ArrayList<Resource>) mapper.readValue(Paths.get(DATA_DIRECTORY_JSON).toFile(),
                    new TypeReference<List<Resource>>() {
                    });

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return resources;
	}

	/**
	 * Method that fetches resources that matches (String tag) parameter. 
	 * @param tag (Name of the tag you want to fetch)
	 * @return returns an ArrayList<Resource> that holds resources with the matching tags.
	 */
	public static ArrayList<Resource> fetchTagData(String tag) {
		ArrayList<Resource> tagList = new ArrayList<Resource>();
		ArrayList<Resource> rs = readData();

		try {

			for (Resource resource : rs) {
				for (String tg : resource.getTags()) {
					if (tg.toLowerCase().replace(" ", "-").contentEquals(tag.toLowerCase())) {
						tagList.add(resource);
					}
				}
			}
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return tagList;
	}
	
	
	/**
	 * Method that searches through all resources and add their tags to  a ArrayList<String>.
	 * Checks if tag exist before adding, to avoid duplicates.
	 * @return ArrayList<String> with all tags.
	 */
	public static ArrayList<String> fetchTags() {
		ArrayList<String> tags = new ArrayList<String>();
		ArrayList<Resource> resources = readData();

		try {
			for (Resource res : resources) {
				for (String tg :res.getTags()) {
					if (!tags.contains(tg)) {
						
						// Replaces spaces with "-", this is to avoid errors in URL mapping.
						tags.add(tg.replace(" ", "-"));
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	return tags;
	}
}