package oldTest;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pl.edu.agh.kis.interlight.model.JCuboid;
import pl.edu.agh.kis.interlight.model.JCylinder;
import pl.edu.agh.kis.interlight.model.JLightPoint;
import pl.edu.agh.kis.interlight.model.JLightSource;
import pl.edu.agh.kis.interlight.model.JScene;





public class MainSceneL {

     
    public static void main(String[] args) throws IOException {
    	
    	
    	
    	    	
    	JScene scene = createScene();
    	
    	
    	JCuboid cuboid = createCuboid();
    	JCuboid cuboid1 = createCuboid1();
    	List<JCuboid> cuboids = new LinkedList<>();
    	cuboids.add(cuboid);
    	cuboids.add(cuboid1);
    	
    	
    	JCylinder cylinder = createCylinder();
    	JCylinder cylinder1 = createCylinder();
    	JCylinder cylinder2 = createCylinder();
    	List<JCylinder> cylinders = new LinkedList<>();
    	cylinders.add(cylinder);
    	cylinders.add(cylinder1);
    	cylinders.add(cylinder2);
    	
    	
    	
    	JLightPoint lightPoint = createLightPoint();
    	List<JLightPoint> lightPoints = new LinkedList<>();
    	lightPoints.add(lightPoint);
    	lightPoints.add(lightPoint);
    	
    	
    	
    	JLightSource lightSource = createLightSource();
    	List<JLightSource> lightSources = new LinkedList<>();
    	lightSources.add(lightSource);
    	lightSources.add(lightSource);
    	lightSources.add(lightSource);
    	lightSources.add(lightSource);
    	
    	
    	
		    	    	
    	
    	
        
        
        
        
        
        
        
      
        
        JsonArrayBuilder arrayCuboidsBuilder = Json.createArrayBuilder();
        
	        for(JCuboid jcuboid : cuboids)  {
	      	  
	        	arrayCuboidsBuilder.add(createCuboidToArray(jcuboid));
	      	  
	      	}
        
        JsonArray arrayCuboids = arrayCuboidsBuilder.build();
        
      //  System.out.println("TO jest arrayCuboids: \n" + arrayCuboids);
        
        
        
        
        
        
        
        
        
        
        
        
        JsonArrayBuilder arrayCylinderBuilder = Json.createArrayBuilder();
        
	        for(JCylinder jcylinder : cylinders)  {
	      	  
	        	arrayCylinderBuilder.add(createCylinderToArray(jcylinder));
	      	  
	      	}
        
        JsonArray arrayCylinders = arrayCylinderBuilder.build();
                
     //   System.out.println("TO jest arrayCylinders: \n" + arrayCylinders);
        
        
        
        
        
        
        
        
        
        JsonArrayBuilder arrayLightPointsBuilder = Json.createArrayBuilder();
        
        for(JLightPoint jlightpoint : lightPoints)  {
      	  
        	arrayLightPointsBuilder.add(createLightPointToArray(jlightpoint));
      	  
      	}
    
        JsonArray arrayLightPoints = arrayLightPointsBuilder.build();
        
      //  System.out.println("TO jest arrayLightPoints: \n" + arrayLightPoints);
        
        
        
        
        
        
    
    	
        
        
        JsonArrayBuilder arrayLightSourcesBuilder = Json.createArrayBuilder();
        
	        for(JLightSource jlightsource : lightSources)  {
	      	  
	        	arrayLightSourcesBuilder.add(createLightSourceToArray(jlightsource));
	      	  
	      	}
    
        JsonArray arrayLightSources = arrayLightSourcesBuilder.build();
        
      //  System.out.println("TO jest arrayLightSources: \n" + arrayLightSources);
        
        
    	
    	
    	
    	
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject sceneObject = factory.createObjectBuilder()
        
        		
        		.add("Name", scene.getScene_name())
        		.add("Scene width", scene.getScene_width())
        		.add("Scene length", scene.getScene_length())
        		.add("Scene height", scene.getRoom_height())
        		
        		
        		
        		.add("Cuboids:", arrayCuboids)
        		.add("Cylinders:", arrayCylinders)
        		.add("Light Points:", arrayLightPoints)
        		.add("Light Sources:", arrayLightSources)
        				
        				
        				
               		     
        		
        		.add("Scene", "End")	
                .build();
        
        
        
        System.out.println("Object: \n" + sceneObject);
        
        
        

        
        
        
      //write to file
        OutputStream os;
		try {
			os = new FileOutputStream("Scenetemp.json");
			JsonWriter jsonWriter = Json.createWriter(os);
	        /**
	         * We can get JsonWriter from JsonWriterFactory also
	        JsonWriterFactory factory = Json.createWriterFactory(null);
	        jsonWriter = factory.createWriter(os);
	        */
	        jsonWriter.writeObject(sceneObject);
	        jsonWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        
    }
    
    
    
    
    
    
    
    
    
    public static JScene createScene() {
    	 
        JScene scene = new JScene();
        scene.setScene_name("Scene 1");
        scene.setScene_width(5000);
        scene.setScene_length(4000);
        scene.setRoom_height(3000);
         
        return scene;
    }
    
    public static JCuboid createCuboid() {
   	 
        JCuboid cuboid = new JCuboid();
        cuboid.setCuboid_pointBaseTopLeftX(2);
        cuboid.setCuboid_pointBaseTopLeftY(2);
        cuboid.setCuboid_height(234);
        cuboid.setCuboid_width(5000);
        cuboid.setCuboid_length(4000);
        cuboid.setCuboid_rotation(3000);
        cuboid.setCuboid_workspace(true);
         
        return cuboid;
        
    }
    
    public static JCuboid createCuboid1() {
      	 
        JCuboid cuboid = new JCuboid();
        cuboid.setCuboid_pointBaseTopLeftX(12);
        cuboid.setCuboid_pointBaseTopLeftY(12);
        cuboid.setCuboid_height(1234);
        cuboid.setCuboid_width(15000);
        cuboid.setCuboid_length(14000);
        cuboid.setCuboid_rotation(13000);
        cuboid.setCuboid_workspace(true);
         
        return cuboid;
        
    }
    
    public static JCylinder createCylinder() {
      	 
        JCylinder cylinder = new JCylinder();
        cylinder.setCylinder_pointBaseCenterX(2);
        cylinder.setCylinder_pointBaseCenterY(2);
        cylinder.setCylinder_radiusX(234);
        cylinder.setCylinder_radiusY(5000);
        cylinder.setCylinder_height(4000);
        cylinder.setCylinder_rotation(3000);
        cylinder.setCylinder_workspace(true);
         
        return cylinder;
        
    }
    
    public static JLightPoint createLightPoint() {
     	 
        JLightPoint lightPoint = new JLightPoint();
        lightPoint.setLightPoint_X(2);
        lightPoint.setLightPoint_Y(2);
        lightPoint.setLightPoint_height(234);
        
     //   double[][] light_points = null;
        //light_points = new double [2][3];
      //  light_points [0][0] = 2.0;
      //  light_points [0][1] = 3.0;
      //  lightPoint.setLight_points(light_points);
        
    //    List<List<Double>> light_points = null;
     //   light_points.addAll((Collection<? extends List<Double>>) Arrays.asList(1.0, Arrays.asList(3.0)));
        
      //  lightPoint.setLight_points(light_points);
        //lightPoint.setLight_points(Arrays.asList(1.0, Arrays.asList(3.0)));
        //light_points.addAll((Collection<? extends List<Double>>) Arrays.asList(1.0, Arrays.asList(3.0)));
        //group.add(Arrays.asList(1.0, Arrays.asList(3.0));
        return lightPoint;
        
    }
    
    public static JLightSource createLightSource() {
     	 
        JLightSource lightSource = new JLightSource();
        lightSource.setLightSource_ies("source");
        lightSource.setLightSource_power(20);
      //  lightSource.setLightSource_dimming(23);
                
        return lightSource;
        
    }
    
    
    
    
    
    
    
    public static JsonArray createCuboidToArray(JCuboid jcuboid) {
	   
	   JsonArrayBuilder builder = Json.createArrayBuilder();
             	  
		   builder.add("Point Base Top Left X"+ jcuboid.getCuboid_pointBaseTopLeftX());
		   builder.add("Point Base Top Left Y"+ jcuboid.getCuboid_pointBaseTopLeftY());
		   builder.add("Height"+ jcuboid.getCuboid_height());
		   builder.add("Width"+ jcuboid.getCuboid_width());
		   builder.add("Length"+ jcuboid.getCuboid_length());
		   builder.add("Rotation"+ jcuboid.getCuboid_rotation());
		   builder.add("Workspace"+ jcuboid.getCuboid_workspace());
	       	  
       JsonArray array= builder.build();
	   
       return array;
    }
    
   
    
   public static JsonArray createCylinderToArray(JCylinder jcylinder) {
	   
	   JsonArrayBuilder builder = Json.createArrayBuilder();
             	  
		   builder.add("Point Base Center X"+ jcylinder.getCylinder_pointBaseCenterX());
		   builder.add("Point Base Center Y"+ jcylinder.getCylinder_pointBaseCenterY());
		   builder.add("Radius X"+ jcylinder.getCylinder_radiusX());
		   builder.add("Radius Y"+ jcylinder.getCylinder_radiusY());
		   builder.add("Height"+ jcylinder.getCylinder_height());
		   builder.add("Rotation"+ jcylinder.getCylinder_rotation());
		   builder.add("Workspace"+ jcylinder.getCylinder_workspace());
	       	  
       JsonArray array= builder.build();
	   
       return array;
    }
    
   
   
   public static JsonArray createLightPointToArray(JLightPoint jlightpoint) {
	   
	   JsonArrayBuilder builder = Json.createArrayBuilder();
             	  
		   builder.add(jlightpoint.getLightPoint_X());
		   builder.add(jlightpoint.getLightPoint_Y());
		   builder.add(jlightpoint.getLightPoint_height());		   
	       	  
       JsonArray array= builder.build();
	   
       return array;
    }
   
   
   
   public static JsonArray createLightSourceToArray(JLightSource jlightsource) {
	   
	   JsonArrayBuilder builder = Json.createArrayBuilder();
             	  
		   builder.add("Ies"+ jlightsource.getLightSource_ies());
		   builder.add("Power"+ jlightsource.getLightSource_power());
		 //  builder.add("Dimming"+ jlightsource.getLightSource_dimming());		   
		   
       JsonArray array= builder.build();
	   
       return array;
    }
   
}

