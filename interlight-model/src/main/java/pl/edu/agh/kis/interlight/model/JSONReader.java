package pl.edu.agh.kis.interlight.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
 

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pl.edu.agh.kis.interlight.datamodel.sets.ISceneSet;



public class JSONReader {


	
	private JScene scene;
	private List<JCuboid> cuboids = new LinkedList<>();;
	private List<JCylinder> cylinders = new LinkedList<>();;
	private List<JLightPoint> lightPoints = new LinkedList<>();;
	private List<JLightSource> lightSources = new LinkedList<>();;
	private List<JPoint> points = new LinkedList<>();
	
	String address;

	
	public JSONReader(String address){
		
		
		this.address = address;	
		
	}
	
	
	public JSceneObject read(){
			 
			JSONParser parser = new JSONParser();
			 
			try {
			 
			Object obj = parser.parse(new FileReader(address));
			 
			JSONObject jsonObject = (JSONObject) obj;
			 
			String scene_name = (String) jsonObject.get("Name:");
			 
			Double scene_width = (Double) jsonObject.get("Scene width:");
			
			Double scene_length = (Double) jsonObject.get("Scene length:");
			
			Double room_height = (Double) jsonObject.get("Room height:");
			
			
			
			scene = createScene(scene_name, scene_width, scene_length, room_height);
		    	 
			//System.out.println(scene);
			 
			 
			 
			 
			JSONArray array_cuboids = (JSONArray) jsonObject.get("Cuboids:");
			Iterator<JSONObject> iterator_cuboids = array_cuboids.iterator();
			

			JCuboid cuboid = new JCuboid();
			 
				while (iterator_cuboids.hasNext()) {
					
					 JSONObject jsonObj = (JSONObject) iterator_cuboids.next();
					 
					 String scuboid_pointBaseTopLeftX = (String) jsonObj.get("Point Base Top Left X").toString();
					 double cuboid_pointBaseTopLeftX = Double.parseDouble(scuboid_pointBaseTopLeftX);
					 
					 String scuboid_pointBaseTopLeftY = (String) jsonObj.get("Point Base Top Left Y").toString();
					 double cuboid_pointBaseTopLeftY = Double.parseDouble(scuboid_pointBaseTopLeftY);
					 
					 String scuboid_height = (String) jsonObj.get("Height").toString();
					 double cuboid_height = Double.parseDouble(scuboid_height);
					 
					 String scuboid_width = (String) jsonObj.get("Width").toString();
					 double cuboid_width = Double.parseDouble(scuboid_width);
					 
					 String scuboid_length = (String) jsonObj.get("Length").toString();
					 double cuboid_length = Double.parseDouble(scuboid_length);
					 
					 String scuboid_rotation = (String) jsonObj.get("Rotation").toString();
					 int cuboid_rotation = Integer.parseInt(scuboid_rotation);
					
					 boolean cuboid_workspace = (boolean) jsonObj.get("Workspace");
					 
					 

						
					 cuboid = createCuboid(cuboid_pointBaseTopLeftX, cuboid_pointBaseTopLeftY, cuboid_height,
								cuboid_width, cuboid_length, cuboid_rotation, cuboid_workspace);
					 
					 cuboids.add(cuboid);
					 
					
				}
				
				//System.out.println(cuboids);
				 
				 
				  
				
				
				
				
				
			JSONArray array_cylinders = (JSONArray) jsonObject.get("Cylinders:");
			Iterator<JSONObject> iterator_cylinders = array_cylinders.iterator();
			

	        JCylinder cylinder = new JCylinder();
	        
				while (iterator_cylinders.hasNext()) {
					
					JSONObject jsonObj = (JSONObject) iterator_cylinders.next();
					
					String scylinder_pointBaseCenterX = (String) jsonObj.get("Point Base Center X").toString();
					double cylinder_pointBaseCenterX = Double.parseDouble(scylinder_pointBaseCenterX);
					
					String scylinder_pointBaseCenterY = (String) jsonObj.get("Point Base Center Y").toString();
					double cylinder_pointBaseCenterY = Double.parseDouble(scylinder_pointBaseCenterY);
							 
					String scylinder_radiusX = (String) jsonObj.get("Radius X").toString();
					double cylinder_radiusX = Double.parseDouble(scylinder_radiusX);
							 
					String scylinder_radiusY = (String) jsonObj.get("Radius Y").toString();
					double cylinder_radiusY = Double.parseDouble(scylinder_radiusY);
					
					String scylinder_height = (String) jsonObj.get("Height").toString();
					double cylinder_height = Double.parseDouble(scylinder_height);
							 
					String scylinder_rotation = (String) jsonObj.get("Rotation").toString();
					int cylinder_rotation = Integer.parseInt(scylinder_rotation);
							
					boolean cylinder_workspace = (boolean) jsonObj.get("Workspace");
							 
							
					
					cylinder = createCylinder(cylinder_pointBaseCenterX, cylinder_pointBaseCenterY, cylinder_radiusX, 
							cylinder_radiusY, cylinder_height, cylinder_rotation, cylinder_workspace);
				     
					cylinders.add(cylinder);
					
					
					}
				
				//System.out.println(cylinders);
					
					
					
				
				
				
				
				JSONArray array_light_points = (JSONArray) jsonObject.get("Light Points:");
				Iterator<JSONObject> iterator_light_points = array_light_points.iterator();
				
				JLightPoint lightPoint = new JLightPoint();
				
					while (iterator_light_points.hasNext()) {
						
						JSONObject jsonObj = (JSONObject) iterator_light_points.next();
						
						String slightPoint_id = (String) jsonObj.get("ID").toString();
						int lightPoint_id = Integer.parseInt(slightPoint_id);
						
						String slightPoint_x = (String) jsonObj.get("X").toString();
						double lightPoint_x = Double.parseDouble(slightPoint_x);
						
						String slightPoint_y = (String) jsonObj.get("Y").toString();
						double lightPoint_y = Double.parseDouble(slightPoint_y);
								 
						String slightPoint_height = (String) jsonObj.get("Height").toString();
						double lightPoint_height = Double.parseDouble(slightPoint_height);
								
						
						lightPoint = createLightPoint(lightPoint_id, lightPoint_x, lightPoint_y, lightPoint_height);
										     	 
					    lightPoints.add(lightPoint);    
						
						
						}
					
					//System.out.println(lightPoints);
					
					
					
					
					
				JSONArray array_light_source = (JSONArray) jsonObject.get("Light Sources:");
				Iterator<JSONObject> iterator_light_source = array_light_source.iterator();

		        JLightSource lightSource = new JLightSource();
		        
					while (iterator_light_source.hasNext()) {
							
						JSONObject jsonObj = (JSONObject) iterator_light_source.next();
							
						String lightSource_ies = (String) jsonObj.get("IES");
							
						String slightSource_power = (String) jsonObj.get("Power").toString();
						double lightSource_power = Double.parseDouble(slightSource_power);
									 
						//String slightSource_dimming = (String) jsonObj.get("Dimming").toString();
						//int lightSource_dimming = Integer.parseInt(slightSource_dimming);
									 
									
						lightSource = createLightSource(lightSource_ies, lightSource_power);
						
						lightSources.add(lightSource);
					     	 
											
						}
					
					//System.out.println(lightSources);
					
					
					
					
					
					
					
				JSONArray array_points = (JSONArray) jsonObject.get("Room points:");
				Iterator<JSONObject> iterator_points = array_points.iterator();

		        JPoint point = new JPoint();
		        
					while (iterator_points.hasNext()) {
							
						JSONObject jsonObj = (JSONObject) iterator_points.next();
							
						String spoint_x = (String) jsonObj.get("X").toString();
						double point_x = Double.parseDouble(spoint_x);
							
						String spoint_y = (String) jsonObj.get("Y").toString();
						double point_y = Double.parseDouble(spoint_y);
									 
						
						
						point = createPoint(point_x, point_y);
						
						points.add(point);
					    	 
								
						}
				 
					//System.out.println(points);
				
			    
		
				
			 
			} catch (FileNotFoundException e) {
			 e.printStackTrace();
			 } catch (IOException e) {
			 e.printStackTrace();
			 } catch (ParseException e) {
			 e.printStackTrace();
			 }
			
			
			
			return new JSceneObject(cuboids, cylinders, lightPoints, lightSources, points, scene);
			
			
			
			 
		}
			 





		public static JScene createScene(String scene_name, double scene_width, 
				double scene_length, double scene_height) {
	    	 
	        JScene scene = new JScene();
	        scene.setScene_name(scene_name);
	        scene.setScene_width(scene_width);
	        scene.setScene_length(scene_length);
	        scene.setRoom_height(scene_height);
	         
	        return scene;
	    }
		
		
		
		
		
		public static JCuboid createCuboid(double cuboid_pointBaseTopLeftX, double cuboid_pointBaseTopLeftY, 
				double cuboid_height, double cuboid_width, double cuboid_length, 
				int cuboid_rotation, boolean cuboid_workspace) {
		   	 
	        JCuboid cuboid = new JCuboid();
	        cuboid.setCuboid_pointBaseTopLeftX(cuboid_pointBaseTopLeftX);
	        cuboid.setCuboid_pointBaseTopLeftY(cuboid_pointBaseTopLeftY);
	        cuboid.setCuboid_height(cuboid_height);
	        cuboid.setCuboid_width(cuboid_width);
	        cuboid.setCuboid_length(cuboid_length);
	        cuboid.setCuboid_rotation(cuboid_rotation);
	        cuboid.setCuboid_workspace(cuboid_workspace);
	         
	        return cuboid;
	        
	    }
	    

		
		
		
		
		public static JCylinder createCylinder(double cylinder_pointBaseCenterX, double cylinder_pointBaseCenterY, 
				double cylinder_radiusX, double cylinder_radiusY, double cylinder_height, 
				int cylinder_rotation, boolean cylinder_workspace) {
	      	 
	        JCylinder cylinder = new JCylinder();
	        cylinder.setCylinder_pointBaseCenterX(cylinder_pointBaseCenterX);
	        cylinder.setCylinder_pointBaseCenterY(cylinder_pointBaseCenterY);
	        cylinder.setCylinder_radiusX(cylinder_radiusX);
	        cylinder.setCylinder_radiusY(cylinder_radiusY);
	        cylinder.setCylinder_height(cylinder_height);
	        cylinder.setCylinder_rotation(cylinder_rotation);
	        cylinder.setCylinder_workspace(cylinder_workspace);
	         
	        return cylinder;
	        
	    }






		public static JLightPoint createLightPoint(int lightPoint_id, double lightPoint_x, 
				double lightPoint_y, double lightPoint_height) {
	     	 
	        JLightPoint lightPoint = new JLightPoint();
	        lightPoint.setLightPoint_id(lightPoint_id);
	        lightPoint.setLightPoint_X(lightPoint_x);
	        lightPoint.setLightPoint_Y(lightPoint_y);
	        lightPoint.setLightPoint_height(lightPoint_height);
	        
	        return lightPoint;
	        
	    }

		
		
		
		
		public static JLightSource createLightSource(String lightSource_ies, double lightSource_power){
	     	 
	        JLightSource lightSource = new JLightSource();
	        lightSource.setLightSource_ies(lightSource_ies);
	        lightSource.setLightSource_power(lightSource_power);
	        //lightSource.setLightSource_dimming(lightSource_dimming);
	                
	        return lightSource;
	        
	    }





		public static JPoint createPoint(double point_x, double point_y) {
	    	 
	        JPoint point = new JPoint();
	        point.setPoint_X(point_x);
	        point.setPoint_Y(point_y);
	                
	        return point;
	        
	    }



}
