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
import org.json.simple.parser.ParseException;

import pl.edu.agh.kis.interlight.datamodel.ICuboid;
import pl.edu.agh.kis.interlight.datamodel.ICylinder;
import pl.edu.agh.kis.interlight.datamodel.ILightPoint;
import pl.edu.agh.kis.interlight.datamodel.ILightSource;
import pl.edu.agh.kis.interlight.datamodel.IRoom;
import pl.edu.agh.kis.interlight.datamodel.IScene;
import pl.edu.agh.kis.interlight.datamodel.sets.ISceneSet;
import pl.edu.agh.kis.interlight.datamodel.util.IPoint;


public class JSONToData {

	
	
	private static List<ICuboid> cuboids = new LinkedList<>();
	private static List<ICylinder> cylinders = new LinkedList<>();
	private static List<ILightPoint> lightPoints = new LinkedList<>();
	private static List<ILightSource> lightSources = new LinkedList<>();
	private static List<IPoint> points = new LinkedList<>();
	


		public static ISceneSet main(String adress) {
			
			
			JSONReader json = new JSONReader(adress);
	    	
			JSceneObject sceneObj = json.read();
	    	
			
			
			IScene scene = new IScene(sceneObj.getScene().getScene_name(), sceneObj.getScene().getScene_width(), 
					sceneObj.getScene().getScene_length());
			
			
			//System.out.println(scene);
			
	        for(JPoint jpoint : sceneObj.getPoints())  {
	        	
	        	IPoint point = new IPoint(jpoint.getPoint_X(), jpoint.getPoint_Y());
	        	
	        	points.add(point);
	        	
	        }
	        
	        
	    	IRoom room = new IRoom(sceneObj.getScene().getRoom_height(), points);
	    	
	    	//System.out.println(room);
	    	
	        
	        
	        	
	        
	        for(JCuboid jcuboid : sceneObj.getCuboids())  {
	        		        	
				IPoint pointCuboid = new IPoint(jcuboid.getCuboid_pointBaseTopLeftX(), 
	            		jcuboid.getCuboid_pointBaseTopLeftY());
	            

	            ICuboid cuboid = new ICuboid(pointCuboid, jcuboid.getCuboid_height(), jcuboid.getCuboid_width(),
	            		jcuboid.getCuboid_length(), jcuboid.getCuboid_rotation(), jcuboid.getCuboid_workspace());
	        	
	        	           
	            cuboids.add(cuboid);
	        	
	        }
	        
	        //System.out.println(cuboids);
	        
	        
	        
	        for(JCylinder jcylinder : sceneObj.getCylinders())  {
	        	
				IPoint pointCylinder = new IPoint(jcylinder.getCylinder_pointBaseCenterX(), 
						jcylinder.getCylinder_pointBaseCenterY());
	            

	            ICylinder cylinder = new ICylinder(pointCylinder, jcylinder.getCylinder_radiusX(), 
	            		jcylinder.getCylinder_radiusY(), jcylinder.getCylinder_height(),
	            		jcylinder.getCylinder_rotation(), jcylinder.getCylinder_workspace());
	        	           
	            cylinders.add(cylinder);
	        	
	        }
	        
	        //System.out.println(cylinders);
	        
	        
	        
	        for(JLightPoint jlightPoint : sceneObj.getLightPoints())  {
	        	
	        	IPoint pointLightPoint = new IPoint(jlightPoint.getLightPoint_X(),
	        			jlightPoint.getLightPoint_Y());
	            

	            ILightPoint lightPoint  = new ILightPoint(jlightPoint.getLightPoint_id(), pointLightPoint, jlightPoint.getLightPoint_height());
	        	           
	            lightPoints.add(lightPoint);
	        	
	        }
	        //System.out.println(lightPoints);
	        
	        
	        
	        for(JLightSource jlightSource : sceneObj.getLightSources())  {
	        	            

	        	ILightSource lightSource  = new ILightSource(jlightSource.getLightSource_ies(),
	        			jlightSource.getLightSource_power());
	        	           
	        	lightSources.add(lightSource);
	        	
	        }
	        //System.out.println(lightSources);
	        	
			
			
			return new ISceneSet(cuboids, cylinders, lightPoints, lightSources, room, scene);
			 
		}



}

