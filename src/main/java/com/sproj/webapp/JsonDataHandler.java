package com.sproj.webapp;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataHandler {

	public final static String DATA_DIRECTORY = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator;

	public final static String DATA_DIRECTORY_JSON = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator+ "resourcedata" + File.separator + "resourceData.json";
	
	public static List<Resource> allResources = readData();
	

	public static void addResource(Resource newResource){
		try {
			allResources.add(newResource);
			writeData(allResources);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static ArrayList<Resource> readData() {
		ArrayList<Resource> resources = new ArrayList<Resource>();
		try {
		    // create object mapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // convert JSON array to list of books
		    resources = (ArrayList<Resource>) mapper.readValue(Paths.get(DATA_DIRECTORY_JSON).toFile(),
                    new TypeReference<List<Resource>>() {
                    });

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return resources;
	}

	public static void writeData(List<Resource> resources) {
		try {

		    // create ObjectMapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // Use mapper to write json file
		    mapper.writeValue(Paths.get(DATA_DIRECTORY_JSON).toFile(), resources);

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	public static ArrayList<Resource> fetchTagData(String tag) {
		ArrayList<Resource> tagList = new ArrayList<Resource>();
		try {
			ArrayList<Resource> rs = readData();

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
	
	
	public static ArrayList<String> fetchTags() {
		ArrayList<String> tags = new ArrayList<String>();

		try {
			ArrayList<Resource> resources = readData();
			for (Resource res : resources) {
				for (String tg :res.getTags()) {
					if (!tags.contains(tg)) {
						tags.add(tg.replace(" ", "-"));
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	return tags;
	}
	
	public static Resource fetchResource(String name) {
		Resource res;
		ArrayList<Resource> resources = readData();
		int finalIndex = 0;
		int index = 0;

		for (Resource resource : resources) {
			index++;
			if (resource.getName().contentEquals(name)) {
				finalIndex = index;
			}
		}
	return resources.get(finalIndex);
	}
	
}