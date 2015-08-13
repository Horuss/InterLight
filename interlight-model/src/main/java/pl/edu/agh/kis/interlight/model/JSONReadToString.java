package pl.edu.agh.kis.interlight.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONReadToString {
	
	
	
	
	public static String readPrettyJson(String address){
			
			
		JSONReader json = new JSONReader(address);
		JSceneObject sceneObj = json.read();
		JSONCreator jsonCreator = new JSONCreator(sceneObj.getScene(), sceneObj.getCuboids(), sceneObj.getCylinders(), sceneObj.getLightPoints(), sceneObj.getLightSources(), sceneObj.getPoints());
	    String jsonString = jsonCreator.createJSONString();
	     
	  //³adny JSON        
	    JsonParser parser = new JsonParser();
	    JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
	
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    String prettyJson = gson.toJson(jsonObject);
	    
	    //System.out.println(prettyJson);
	    
	    return prettyJson;
	
	     
	}	

}