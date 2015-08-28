package pl.edu.agh.kis.intelight.schema;

import java.io.File;
import java.io.IOException;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;


public class JSONSchema {

	public static boolean validate(String jsonFile) throws IOException, ProcessingException
	{
	    //File schemaFile = new File("schema_interlight.json");
	    //File jsonFile = new File("1Scena_scene.json");
		
		
		
		String schemaFile = "{"
	    + "\"$schema\": \"http://json-schema.org/draft-04/schema#\","
	    + "\"title\": \"Scene\","
	    + "\"description\": \"Scene Interlight\","
	    + "\"type\": \"object\","
	    + "\"properties\": {"
	        + "\"Name:\": {"
	            + "\"description\": \"Scene name\","
	            + "\"type\": \"string\","
				+ "\"minimum\": 0"
			+ "},"
	        + "\"Scene width:\": {"
	            + "\"description\": \"Scene width\","
	            + "\"type\": \"number\","
				+ "\"minimum\": 0"
			+ "},"
	        + "\"Scene length:\": {"
				+ "\"description\": \"Scene length\","
	            + "\"type\": \"number\","
	            + "\"minimum\": 0"
	        + "},"
			+ "\"Room height:\": {"
				+ "\"description\": \"Scene height\","
	            + "\"type\": \"number\","
	            + "\"minimum\": 0"
	        + "},"
	        + "\"Cuboids:\": {"
	            + "\"type\": \"array\","
	            + "\"items\": {"
	                + "\"type\": \"object\","
						+ "\"properties\": {"
							+ "\"Point Base Top Left X\": {"
								+ "\"description\": \"Point Base Top Left X\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Point Base Top Left Y\": {"
								+ "\"description\": \"Point Base Top Left Y\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Height\": {"
								+ "\"description\": \"Height\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Width\": {"
								+ "\"description\": \"Width\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Length\": {"
								+ "\"description\": \"Length\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Rotation\": {"
								+ "\"description\": \"Rotation\","
	                            + "\"type\": \"integer\""
	                        + "},"
	                        + "\"Workspace\": {"
								+ "\"description\": \"Workspace\","
	                            + "\"type\": \"boolean\""
	                        + "}"
	                  + "},"
	            + "\"minItems\": 1,"
	            + "\"uniqueItems\": false"
	            + "}"
	        + "},"
			+ "\"Cylinders:\": {"
	            + "\"type\": \"array\","
	            + "\"items\": {"
	                + "\"type\": \"object\","
						+ "\"properties\": {"
							+ "\"Point Base Top Center X\": {"
								+ "\"description\": \"Point Base Top Center X\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Point Base Top Center Y\": {"
								+ "\"description\": \"Point Base Top Center Y\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Radius X\": {"
								+ "\"description\": \"Radius X\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Radius Y\": {"
								+ "\"description\": \"Radius Y\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Height\": {"
								+ "\"description\": \"Height\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Rotation\": {"
								+ "\"description\": \"Rotation\","
	                            + "\"type\": \"integer\""
	                        + "},"
	                        + "\"Workspace\": {"
								+ "\"description\": \"Workspace\","
	                            + "\"type\": \"boolean\""
	                        + "}"
	                   + "},"
	            + "\"minItems\": 1,"
	            + "\"uniqueItems\": false"
	            + "}"
	        + "},"
			+ "\"Light Points:\": {"
	            + "\"type\": \"array\","
	            + "\"items\": {"
	                + "\"type\": \"object\","
						+ "\"properties\": {"
							+ "\"ID\": {"
								+ "\"description\": \"ID\","
	                            + "\"type\": \"integer\""
	                        + "},"
	                        + "\"X\": {"
								+ "\"description\": \"X\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Y\": {"
								+ "\"description\": \"Y\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Height\": {"
								+ "\"description\": \"Height\","
	                            + "\"type\": \"number\""
	                        + "}"
	                   + "},"
	            + "\"minItems\": 1,"
	            + "\"uniqueItems\": false"
	            + "}"
	        + "},"
			+ "\"Light Sources:\": {"
	            + "\"type\": \"array\","
	            + "\"items\": {"
	                + "\"type\": \"object\","
						+ "\"properties\": {"
							+ "\"IES\": {"
								+ "\"description\": \"IES\","
	                            + "\"type\": \"string\""
	                        + "},"
	                        + "\"Power\": {"
							+ "\"description\": \"Power\","
	                            + "\"type\": \"number\""
	                        + "}"
	                    + "},"
	            + "\"minItems\": 1,"
	            + "\"uniqueItems\": false"
	            + "}"
	        + "},"
			+ "\"Room points:\": {"
	            + "\"type\": \"array\","
	            + "\"items\": {"
	                + "\"type\": \"object\","
						+ "\"properties\": {"
							+ "\"X\": {"
								+ "\"description\": \"X\","
	                            + "\"type\": \"number\""
	                        + "},"
	                        + "\"Y\": {"
								+ "\"description\": \"Y\","
	                            + "\"type\": \"number\""
	                        + "}"
	                    + "},"
	            + "\"minItems\": 1,"
	            + "\"uniqueItems\": false"
	            + "}"
	       + "}"
	    + "},"
	    + "\"required\": [\"Name:\", \"Scene width:\", \"Scene length:\", \"Room height:\", \"Cuboids:\"]"
	    + "}"; 
		
	    
	    
	    
	    
	    try{
	    	if (ValidationJSON.isJsonValid(schemaFile, jsonFile)){
	    		return true;
	        }else{
	        	return false;
	        }
	    	
	    }catch(IOException e) {
	    	return false;
	   	 }
	    catch(ProcessingException e) {
	    	return false;
	      	 }
	    
	}
}
	    

