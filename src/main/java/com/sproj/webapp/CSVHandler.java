package com.sproj.webapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public final class CSVHandler {

	public final static String DIRECTORY_DATA = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator;
	public final static String DIRECTORY_RESOURCE_DATA = "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "static" + File.separator + "data" + File.separator + "resourcedata" + File.separator;

	public static void readCSV(){

		BufferedReader fileReader = null;
	    CSVParser csvParser = null;
	 
	    try {
	      fileReader = new BufferedReader(new FileReader(DIRECTORY_RESOURCE_DATA + "resourceData.csv"));
	      csvParser = new CSVParser(fileReader,
	          CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
	 
	      List<Resource> resources = new ArrayList<Resource>();
	      
	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      
	      for (CSVRecord csvRecord : csvRecords) {
	        Resource resource = new Resource(
	            csvRecord.get("name"),
	            csvRecord.get("website"),
	            csvRecord.get("description"),
	            csvRecord.get("tags")
	            );
	        
	        resources.add(resource);
	      }
	 
	      for (Resource resource : resources) {
	        System.out.println(resource);
	      }
	 
	    } catch (Exception e) {
	      System.out.println("Reading CSV Error!");
	      e.printStackTrace();
	    } finally {
	      try {
	        fileReader.close();
	        csvParser.close();
	      } catch (IOException e) {
	        System.out.println("Closing fileReader/csvParser Error!");
	        e.printStackTrace();
	      }
	  }
	 
    }
}