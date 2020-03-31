package com.sproj.webapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;



public final class CSVHandler {
	
	public static void main(String[] args){
//		TEST
		CSVHandler.readData();
		Resource res = new Resource("b","b","b","b");
		CSVHandler.writeData(res);
		CSVHandler.readData();
	}

	public final static String DIRECTORY_DATA = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator;
	public final static String DIRECTORY_RESOURCE_DATA = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator + "resourcedata" + File.separator;


	public static ArrayList<Resource> readData(){

		ArrayList<Resource> resources = new ArrayList<Resource>();

		try {
			// create a reader
			Reader reader = Files.newBufferedReader(Paths.get(DIRECTORY_RESOURCE_DATA + "resourceData.csv"));

			// read csv file
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

			for (CSVRecord record : records) {
				System.out.println("Name: " + record.get("name"));
				System.out.println("Website: " + record.get("website"));
				System.out.println("Description: " + record.get("description"));
				System.out.println("Tags: " + record.get("tags"));
				resources.add(new Resource(record.get("name"), record.get("website"), record.get("description"), record.get("tags")));
			}

			// close the reader
			reader.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return resources;
	}

	public static void writeData(Resource newResource) {

		File resourcePath = new File(DIRECTORY_RESOURCE_DATA + "resourceData.csv");

		try {
			// create a writer
			BufferedWriter writer = new BufferedWriter(new FileWriter(resourcePath, true));

			// write CSV file
			CSVPrinter printer = CSVFormat.DEFAULT.withFirstRecordAsHeader().print(writer);
			printer.printRecord(newResource.getName(), newResource.getWebsite(), newResource.getDescription(), newResource.getTags());


			// flush the stream
			printer.flush();

			// close the writer
			writer.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}