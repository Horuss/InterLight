package oldTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readtest {

	public static void main(String[] args) {
		 
		JSONParser parser = new JSONParser();
		 
		try {
		 
		Object obj = parser.parse(new FileReader("Scenaaaa_scene.json"));
		 
		JSONObject jsonObject = (JSONObject) obj;
		 
		String scene_name = (String) jsonObject.get("Name:");
		System.out.println(scene_name);
		 
		Double scene_width = (Double) jsonObject.get("Scene width:");
		System.out.println(scene_width);
		
		Double scene_length = (Double) jsonObject.get("Scene length:");
		System.out.println(scene_length);
		
		Double scene_height = (Double) jsonObject.get("Scene height:");
		System.out.println(scene_height);
		
		
		 
		 
		 
		 
		// loop array
		JSONArray cuboids = (JSONArray) jsonObject.get("Cuboids:");
		Iterator<JSONObject> iterator_cuboids = cuboids.iterator();
		
			while (iterator_cuboids.hasNext()) {
				
				 JSONObject jsonObj = (JSONObject) iterator_cuboids.next();
				 //String cuboid_pointBaseTopLeftX = (String) jsonObj.get("Ies");
				 //System.out.println(cuboid_pointBaseTopLeftX);
				 
				 String scuboid_pointBaseTopLeftX = (String) jsonObj.get("Point Base Top Left X").toString();
				 double cuboid_pointBaseTopLeftX = Double.parseDouble(scuboid_pointBaseTopLeftX);
				 System.out.println(cuboid_pointBaseTopLeftX);
				 
				 String scuboid_pointBaseTopLeftY = (String) jsonObj.get("Point Base Top Left Y").toString();
				 double cuboid_pointBaseTopLeftY = Double.parseDouble(scuboid_pointBaseTopLeftY);
				 System.out.println(cuboid_pointBaseTopLeftY);
				 
				 String scuboid_height = (String) jsonObj.get("Height").toString();
				 double cuboid_height = Double.parseDouble(scuboid_height);
				 System.out.println(cuboid_height);
				 
				 String scuboid_width = (String) jsonObj.get("Width").toString();
				 double cuboid_width = Double.parseDouble(scuboid_width);
				 System.out.println(cuboid_width);
				 
				 String scuboid_length = (String) jsonObj.get("Length").toString();
				 double cuboid_length = Double.parseDouble(scuboid_length);
				 System.out.println(cuboid_length);
				 
				 String scuboid_rotation = (String) jsonObj.get("Rotation").toString();
				 double cuboid_rotation = Double.parseDouble(scuboid_rotation);
				 System.out.println(cuboid_rotation);
				
				 boolean cuboid_workspace = (boolean) jsonObj.get("Workspace");
				 System.out.println(cuboid_workspace);
				 
				
			}
			 
			 
		
			
		JSONArray cylinders = (JSONArray) jsonObject.get("Cylinders:");
		Iterator<JSONObject> iterator_cylinders = cylinders.iterator();
		
			while (iterator_cylinders.hasNext()) {
				
				JSONObject jsonObj = (JSONObject) iterator_cylinders.next();
				
				String scylinder_pointBaseCenterX = (String) jsonObj.get("Point Base Center X").toString();
				double cylinder_pointBaseCenterX = Double.parseDouble(scylinder_pointBaseCenterX);
				System.out.println(cylinder_pointBaseCenterX);
				
				String scylinder_pointBaseCenterY = (String) jsonObj.get("Point Base Center Y").toString();
				double cylinder_pointBaseCenterY = Double.parseDouble(scylinder_pointBaseCenterY);
				System.out.println(cylinder_pointBaseCenterY);
						 
				String scylinder_radiusX = (String) jsonObj.get("Radius X").toString();
				double cylinder_radiusX = Double.parseDouble(scylinder_radiusX);
				System.out.println(cylinder_radiusX);
						 
				String scylinder_radiusY = (String) jsonObj.get("Radius Y").toString();
				double cylinder_radiusY = Double.parseDouble(scylinder_radiusY);
				System.out.println(cylinder_radiusY);
				
				String scylinder_height = (String) jsonObj.get("Height").toString();
				double cylinder_height = Double.parseDouble(scylinder_height);
				System.out.println(cylinder_height);
						 
				String scylinder_rotation = (String) jsonObj.get("Rotation").toString();
				double cylinder_rotation = Double.parseDouble(scylinder_rotation);
				System.out.println(cylinder_rotation);
						
				boolean cylinder_workspace = (boolean) jsonObj.get("Workspace");
				System.out.println(cylinder_workspace);
						 
						
				}
			
			
			
			
			
			JSONArray light_points = (JSONArray) jsonObject.get("Light Points:");
			Iterator<JSONObject> iterator_light_points = light_points.iterator();
			
				while (iterator_light_points.hasNext()) {
					
					JSONObject jsonObj = (JSONObject) iterator_light_points.next();
					
					String slightPoint_x = (String) jsonObj.get("X").toString();
					double lightPoint_x = Double.parseDouble(slightPoint_x);
					System.out.println(lightPoint_x);
					
					String slightPoint_y = (String) jsonObj.get("Y").toString();
					double lightPoint_y = Double.parseDouble(slightPoint_y);
					System.out.println(lightPoint_y);
							 
					String slightPoint_height = (String) jsonObj.get("Height").toString();
					double lightPoint_height = Double.parseDouble(slightPoint_height);
					System.out.println(lightPoint_height);
							
					}
				
				
				
				
			JSONArray light_source = (JSONArray) jsonObject.get("Light Sources:");
			Iterator<JSONObject> iterator_light_source = light_source.iterator();
				
				while (iterator_light_source.hasNext()) {
						
					JSONObject jsonObj = (JSONObject) iterator_light_source.next();
						
					String lightSource_ies = (String) jsonObj.get("IES");
					System.out.println(lightSource_ies);
						
					String slightSource_power = (String) jsonObj.get("Power").toString();
					double lightSource_power = Double.parseDouble(slightSource_power);
					System.out.println(lightSource_power);
								 
					String slightSource_dimming = (String) jsonObj.get("Dimming").toString();
					double lightSource_dimming = Double.parseDouble(slightSource_dimming);
					System.out.println(lightSource_dimming);
								 
								
					}
				
				
				
				
				
				
				
				
			JSONArray points = (JSONArray) jsonObject.get("Points:");
			Iterator<JSONObject> iterator_points = points.iterator();
				
				while (iterator_points.hasNext()) {
						
					JSONObject jsonObj = (JSONObject) iterator_points.next();
						
					String spoint_x = (String) jsonObj.get("X").toString();
					double point_x = Double.parseDouble(spoint_x);
					System.out.println(point_x);
						
					String spoint_y = (String) jsonObj.get("Y").toString();
					double point_y = Double.parseDouble(spoint_y);
					System.out.println(point_y);
								 
							
					}
			 
		 
			
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
			
			
			
		 
		} catch (FileNotFoundException e) {
		 e.printStackTrace();
		 } catch (IOException e) {
		 e.printStackTrace();
		 } catch (ParseException e) {
		 e.printStackTrace();
		 }
		 
		}
		 
		}
