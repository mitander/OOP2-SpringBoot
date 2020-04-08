package com.devtools.webapp;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author carl
 * DataHandler handles writing and reading data to/from JSON file,
 * adding and deleting Resource objects and fetching tags from resouces.
 */
public class DataHandler {

	/**
	 * Path variable to JSON file holding all data.
	 * JSON_DATA :src/main/resources/static/data/resourcedata/resourcedata.json
	 */
	public final static String JSON_DATA = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator + "resourcedata" + File.separator
			+ "resourcedata.json";
	
	public final static String TEST_DATA = "src" + File.separator + "test" + File.separator + "resources"
			+ File.separator + "testdata.json";

	/**
	 * Method for writing an ArrayList to selected file.
	 * Uses Jackson ObjectMapper / Objectwriter / SerializationFeature.INDENT_OUTPUT
	 * to indent and 
	 * 
	 * @param resource you want to add
	 * @param path (path to file)
	 */
	public static void writeData(ArrayList<Resource> resources, String path) {
		try {
			// create ObjectMapper instance
			ObjectMapper mapper = new ObjectMapper();
			
			// create new writer (DefaultPrettyPrinter)
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			
			// Keeps indented structure on json file when adding resources
			mapper.enable(SerializationFeature.INDENT_OUTPUT);

			// Use mapper to write json file
			writer.writeValue(Paths.get(path).toFile(), resources);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Method for adding resources.
	 * 
	 * Adds new resource to an ArrayList<Resource> and writes the new data with writeJsonData().
	 * 
	 * @param resource (resource you want to add)
	 * @param path (path to file)
	 */
	public static void addResource(Resource resource, String path) {
			ArrayList<Resource> resources = fetchJsonData(path); 

			// Reverse arraylist to add new resource on top then reverse 
			// it back to original to keep structure
			Collections.reverse(resources);
			resources.add(resource);
			Collections.reverse(resources);
			
			// Write data
			writeData(resources, path);
	}

	/**
	 * Method for deleting a resource, iterates through existing resources and
	 * delete the index with corresponding id.
	 * 
	 * @param id of resource you want to delete
	 * @param path (path to file)
	 */
	public static void deleteResource(int id, String path) {
		ArrayList<Resource> allResources = fetchJsonData(path);

		try {
			for (Iterator<Resource> iterator = allResources.iterator(); iterator.hasNext();) {
				Resource resource = iterator.next();
				if (resource.getId() == id) {
					iterator.remove();
					
					// Write data
					writeData(allResources, path);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method that uses ObjectMapper from Jackson to convert a JSON array to a
	 * ArrayList with read data.
	 * 
	 * @return an ArrayList<Resource> with read items.
	 * @param path to data
	 */
	public static ArrayList<Resource> fetchJsonData(String path) {
	ArrayList<Resource> resources = new ArrayList<Resource>();
		try {
			// Create object mapper instance
			ObjectMapper mapper = new ObjectMapper();

			// Convert JSON array to ArrayList of resources.
			List<Resource> fetchedResources = Arrays.asList(mapper.readValue(Paths.get(path).toFile(), Resource[].class));

			for (Resource resource : fetchedResources) {
				resources.add(resource);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	return resources;
	}

	/**
	 * Method that fetches resources that matches (String tag) parameter.
	 * 
	 * @param tag (Name of the tag you want to fetch)
	 * @param path to data
	 * @return ArrayList<Resource> that holds resources with the matching tags.
	 */
	public static ArrayList<Resource> fetchTagData(String tag, String path) {
		ArrayList<Resource> tagList = new ArrayList<Resource>();
		ArrayList<Resource> rs = fetchJsonData(path); 
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
	 * Method that searches through all resources and add their tags to a
	 * ArrayList<String>. Checks if tag exist before adding, to avoid duplicates.
	 * 
	 * @return ArrayList<String> with all tags.
	 */
	public static ArrayList<String> fetchTags(String path) {
		ArrayList<String> tags = new ArrayList<String>();
		ArrayList<Resource> resources = fetchJsonData(path);

		try {
			for (Resource res : resources) {
				for (String tg : res.getTags()) {
					if (!tags.contains(tg)) {

						// Replaces spaces with "-", this is to avoid errors in URL mapping.
						tags.add(tg.replace(" ", "-"));

						// Sort tags in alphabetical order
						Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tags;
	}
}