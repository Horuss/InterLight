package pl.edu.agh.kis.interlight.model;


import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;






import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JSONCreator {
	
	
	private JScene scene;
	private List<JCuboid> cuboids;
	private List<JCylinder> cylinders;
	private List<JLightPoint> lightPoints;
	private List<JLightSource> lightSources;
	private List<JPoint> points;
	
	private String JSONString;
	
	
	public JSONCreator(JScene scene, List<JCuboid> cuboids, List<JCylinder> cylinders, 
			List<JLightPoint> lightPoints, List<JLightSource> lightSources, List<JPoint> points){
		
		this.scene = scene;
		this.cuboids = cuboids;
		this.cylinders = cylinders;
		this.lightPoints = lightPoints;
		this.lightSources = lightSources;
		this.points = points;
		
	}
	
	
	
	public void createAndWrite(){
		
		
		 
		
	    JSONArray cuboidsArray = new JSONArray();
        
		    for(JCuboid jcuboid : cuboids)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createCuboidToArray(jcuboid);
	        	cuboidsArray.add(object);
	      	  
	      	}
	    
     
        
     
	    
	    JSONArray cylindersArray = new JSONArray();
        
		    for(JCylinder jcylinder : cylinders)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createCylinderToArray(jcylinder);
	        	cylindersArray.add(object);
	      	  
	      	}
             
     
     
     
   
		JSONArray lightPointsArray = new JSONArray();
	        
			for(JLightPoint jlightpoint : lightPoints)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createLightPointToArray(jlightpoint);
	        	lightPointsArray.add(object);
	      	  
	      	}
        
        
        
	    
	    
	    JSONArray lightSourcesArray = new JSONArray();
             
	        for(JLightSource jlightsource : lightSources)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createLightSourceToArray(jlightsource);
	        	lightSourcesArray.add(object);
	      	  
	      	}
	    
	    
	    
	    
	    JSONArray pointsArray = new JSONArray();
            
	        for(JPoint jpoint : points)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createPointToArray(jpoint);
	        	pointsArray.add(object);
	      	  
	      	}
	    
	    
     
   	
	        JSONObject sceneObject = new JSONObject();
		    
		    	sceneObject.put("Name:", scene.getScene_name());
	     		
		    	sceneObject.put("Scene width:", scene.getScene_width());
	     		
		    	sceneObject.put("Scene length:", scene.getScene_length());
	     		
		    	sceneObject.put("Room height:", scene.getRoom_height());
     		
		    	sceneObject.put("Cuboids:", cuboidsArray);
		    	
		    	sceneObject.put("Cylinders:", cylindersArray);
		    	
		    	sceneObject.put("Light Points:", lightPointsArray);
		    	
		    	sceneObject.put("Light Sources:", lightSourcesArray);
		    	
		    	sceneObject.put("Room points:", pointsArray);

     
     
     
     System.out.println("JSON Object: \n" + sceneObject);
     
          
   //write to file
		
		try {
	    	 
	    	 FileWriter file = new FileWriter(scene.getScene_name()+"_scene.json");
	    	 file.write(sceneObject.toJSONString());
	    	 file.flush();
	    	 file.close();
	    	 
	    	 } catch (IOException e) {
	    	 e.printStackTrace();
	    	 }
     
     
 }
	
	
	
	public String createJSONString(){
		
		
		 
		
	    JSONArray cuboidsArray = new JSONArray();
        
		    for(JCuboid jcuboid : cuboids)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createCuboidToArray(jcuboid);
	        	cuboidsArray.add(object);
	      	  
	      	}
	    
     
        
     
	    
	    JSONArray cylindersArray = new JSONArray();
        
		    for(JCylinder jcylinder : cylinders)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createCylinderToArray(jcylinder);
	        	cylindersArray.add(object);
	      	  
	      	}
             
     
     
     
   
		JSONArray lightPointsArray = new JSONArray();
	        
			for(JLightPoint jlightpoint : lightPoints)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createLightPointToArray(jlightpoint);
	        	lightPointsArray.add(object);
	      	  
	      	}
        
        
        
	    
	    
	    JSONArray lightSourcesArray = new JSONArray();
             
	        for(JLightSource jlightsource : lightSources)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createLightSourceToArray(jlightsource);
	        	lightSourcesArray.add(object);
	      	  
	      	}
	    
	    
	    
	    
	    JSONArray pointsArray = new JSONArray();
            
	        for(JPoint jpoint : points)  {
	      	  
	        	JSONObject object = new JSONObject();
	        	object = createPointToArray(jpoint);
	        	pointsArray.add(object);
	      	  
	      	}
	    
	    
     
   	
	        JSONString = "{\"Name:\":\"" + scene.getScene_name()
	        		+"\",\"Scene width:\":"+ scene.getScene_width()
	        		+",\"Scene length:\":"+ scene.getScene_length()
	        		+",\"Room height:\":"+ scene.getRoom_height()
	        		+",\"Room points:\":"+ pointsArray.toJSONString()
	        		+",\"Cuboids:\":"+ cuboidsArray.toString()
	        		+",\"Cylinders:\":"+ cylindersArray.toJSONString()
	        		+",\"Light Points:\":"+ lightPointsArray.toJSONString()
	        		+",\"Light Sources:\":"+ lightSourcesArray.toJSONString()
	        		+"}";
	        	    	
		    	
		    	
		    	return JSONString;

     
}
 
 
 
        	  
	
	public static JSONObject createCuboidToArray(JCuboid jcuboid) {
		   
		JSONObject object = new JSONObject();  
	             	  
				object.put("Point Base Top Left X", jcuboid.getCuboid_pointBaseTopLeftX());
				object.put("Point Base Top Left Y", jcuboid.getCuboid_pointBaseTopLeftY());
				object.put("Height", jcuboid.getCuboid_height());
				object.put("Width", jcuboid.getCuboid_width());
				object.put("Length", jcuboid.getCuboid_length());
				object.put("Rotation", jcuboid.getCuboid_rotation());
				object.put("Workspace", jcuboid.getCuboid_workspace());				   
		   
	       return object;
	    }
	    

	
	
	public static JSONObject createCylinderToArray(JCylinder jcylinder) {
		   
		JSONObject object = new JSONObject();  
	             	  
				object.put("Point Base Center X", jcylinder.getCylinder_pointBaseCenterX());
				object.put("Point Base Center Y", jcylinder.getCylinder_pointBaseCenterY());
				object.put("Radius X", jcylinder.getCylinder_radiusX());
				object.put("Radius Y", jcylinder.getCylinder_radiusY());
				object.put("Height", jcylinder.getCylinder_height());
				object.put("Rotation", jcylinder.getCylinder_rotation());
				object.put("Workspace", jcylinder.getCylinder_workspace());				   
		   
	       return object;
	    }

	   
	   
	   
	public static JSONObject createLightPointToArray(JLightPoint jlightpoint) {
		   
		JSONObject object = new JSONObject();  
	             	  
				object.put("ID", jlightpoint.getLightPoint_id());
				object.put("X", jlightpoint.getLightPoint_X());
				object.put("Y", jlightpoint.getLightPoint_Y());
				object.put("Height", jlightpoint.getLightPoint_height());
		   
	       return object;
	    }   
			
	


	
	public static JSONObject createLightSourceToArray(JLightSource jlightsource) {
		   
		JSONObject object = new JSONObject();  
	             	  
				object.put("IES", jlightsource.getLightSource_ies());
				object.put("Power", jlightsource.getLightSource_power());
				//object.put("Dimming", jlightsource.getLightSource_dimming());
		   
	       return object;
	    }	
	


	
	
	public static JSONObject createPointToArray(JPoint jpoint) {
		   
		JSONObject object= new JSONObject();  
	             	  
				object.put("X", jpoint.getPoint_X());
				object.put("Y", jpoint.getPoint_Y());
		   
	       return object;
	    }	
	
	
	
	
}
