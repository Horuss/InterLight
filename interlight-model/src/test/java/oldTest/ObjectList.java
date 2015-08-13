package oldTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import pl.edu.agh.kis.interlight.model.JCuboid;
import pl.edu.agh.kis.interlight.model.JCylinder;
import pl.edu.agh.kis.interlight.model.JLightPoint;
import pl.edu.agh.kis.interlight.model.JLightSource;
import pl.edu.agh.kis.interlight.model.JScene;

public class ObjectList {
	
	
	
	   
    public static void main(String[] args) {
    	
    	
    	JScene scene = createScene();
    	JCuboid cuboid = createCuboid();
    	JCuboid cuboid1 = createCuboid1();
    	List<JCuboid> cuboids = new LinkedList<>();
    	cuboids.add(cuboid);
    	cuboids.add(cuboid1);
    	JCylinder cylinder = createCylinder();
    	JLightPoint lightPoint = createLightPoint();
    	JLightSource lightSource = createLightSource();
		    	    	
        JsonObject sceneObject = Json.createObjectBuilder()
        		
        		
        		
        		
        		
        		.add("employees", Json.createArrayBuilder()
        			    .add(Json.createObjectBuilder()
        			      .add("firstName", "John")
        			      .add("lastName", "Doe"))
        			    .add(Json.createObjectBuilder()
        			      .add("firstName", "John")
        			      .add("lastName", "Doe")))
        		
        		
        		.add("Name", scene.getScene_name())
        		.add("Scene width", scene.getScene_width())
        		.add("Scene length", scene.getScene_length())
        		.add("Scene height", scene.getRoom_height())
        		.add("Cuboid",
        			Json.createObjectBuilder().add("Point BaseTop Left X", cuboid.getCuboid_pointBaseTopLeftX())
        									  .add("Point BaseTop Left Y", cuboid.getCuboid_pointBaseTopLeftY())
        									  .add("Height", cuboid.getCuboid_height())	
        									  .add("Width", cuboid.getCuboid_width())
        									  .add("Length", cuboid.getCuboid_length())
        									  .add("Rotation", cuboid.getCuboid_rotation())
        									  .add("Workspace", cuboid.getCuboid_workspace())
        									  .build()
        			)
        		.add("Cuboid1",
        			Json.createObjectBuilder().add("Point BaseTop Left X", cuboid.getCuboid_pointBaseTopLeftX())
        									  .add("Point BaseTop Left Y", cuboid.getCuboid_pointBaseTopLeftY())
        									  .add("Height", cuboid.getCuboid_height())	
        									  .add("Width", cuboid.getCuboid_width())
        									  .add("Length", cuboid.getCuboid_length())
        									  .add("Rotation", cuboid.getCuboid_rotation())
        									  .add("Workspace", cuboid.getCuboid_workspace())
        									  .build()
        			)
        		.add("Cylinder",
        			Json.createObjectBuilder().add("Point Base Center X", cylinder.getCylinder_pointBaseCenterX())
        									  .add("Point Base Center Y", cylinder.getCylinder_pointBaseCenterY())
        									  .add("Radius X", cylinder.getCylinder_radiusX())	
        									  .add("Radius Y", cylinder.getCylinder_radiusY())
        									  .add("Height", cylinder.getCylinder_height())
        									  .add("Rotation", cylinder.getCylinder_rotation())
        									  .add("Workspace", cylinder.getCylinder_workspace())
        									  .build()
        			)
        		.add("Cylinder1",
        			Json.createObjectBuilder().add("Point Base Center X", cylinder.getCylinder_pointBaseCenterX())
        									  .add("Point Base Center Y", cylinder.getCylinder_pointBaseCenterY())
        									  .add("Radius X", cylinder.getCylinder_radiusX())	
        									  .add("Radius Y", cylinder.getCylinder_radiusY())
        									  .add("Height", cylinder.getCylinder_height())
        									  .add("Rotation", cylinder.getCylinder_rotation())
        									  .add("Workspace", cylinder.getCylinder_workspace())
        									  .build()
        			)
        		.add("Light Point",
        			Json.createObjectBuilder().add("X", lightPoint.getLightPoint_X())
        									  .add("Y", lightPoint.getLightPoint_Y())
        									  .add("Height", lightPoint.getLightPoint_height())	
        								//	  .add("List points", lightPoint.getLight_points())
        									  .build()
        			)
        		.add("Light source",
        			Json.createObjectBuilder().add("Ies", lightSource.getLightSource_ies())
        									  .add("Power", lightSource.getLightSource_power())
        						//			  .add("Dimming", lightSource.getLightSource_dimming())
        									  .build()
        			)
        		.add("Scene", "End")	
                .build();
        

        
        
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = factory.createObjectBuilder()
        
        		
        		
        		
        		.add("address", factory.createObjectBuilder()
        				.add("streetAddress", "21 2nd Street")
        				.add("city", "New York")
        				.add("state", "NY")
        				.add("postalCode", "10021"))
        		
        				
        				
        				
        		  		
        		        .build();
        
        System.out.println("Object: \n" + sceneObject);
        System.out.println("Object: \n" + value);
        
        
        
        JsonArrayBuilder builder = Json.createArrayBuilder();
        
        for(int i =0; i<3; i++) {
        	  builder.add("postalCode"+"we");
        	}
        //builder.build();
        JsonArray arr = builder.build();
        System.out.println("Object: \n" + arr);
        
        
        
 JsonArrayBuilder builder1 = Json.createArrayBuilder();
        
 		for(JCuboid jcuboid : cuboids)  {
        	  builder1.add("postalCode"+"we");
        	  
        	  builder1.add("Point BaseTop Left X"+ jcuboid.getCuboid_pointBaseTopLeftX());
        	  builder1.add("Point BaseTop Left Y"+ jcuboid.getCuboid_pointBaseTopLeftY());
        	  builder1.add("Height"+ jcuboid.getCuboid_height());
        	  builder1.add("Width"+ jcuboid.getCuboid_width());
        	  builder1.add("Length"+ jcuboid.getCuboid_length());
        	  builder1.add("Rotation"+ jcuboid.getCuboid_rotation());
        	  builder1.add("Workspace"+ jcuboid.getCuboid_workspace());
          									
          		
        	  builder1.build();
        	  
        	}
        //builder.build();
        JsonArray arr1 = builder1.build();
        System.out.println("Object!!!!!!: \n" + arr1);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        JsonArrayBuilder builder111 = Json.createArrayBuilder();
        
        for(JCuboid jcuboid : cuboids)  {
      	  
        	builder111.add(createArrayCuboids(jcuboid));
      	  
      	}
        
        JsonArray arr111 = builder111.build();
        System.out.println("TO jest to::::::::: \n" + arr111);
        
        
        
        
        
        
        
        
        
        
        JsonBuilderFactory factory1 = Json.createBuilderFactory(null);
        JsonObject value1 = factory1.createObjectBuilder()
        
        		
        		
        		
        		
        		
        		.add("array", arr111)
        				
               		        .build();
        
        System.out.println("Object: \n" + value1);
        
        
        
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
		} catch (IOException e) {
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
     //   lightSource.setLightSource_dimming(23);
                
        return lightSource;
        
    }
    
   public static JsonArray createArrayCuboids(JCuboid jcuboid) {
	   
	   JsonArrayBuilder builder = Json.createArrayBuilder();
             	  
		   builder.add("Point BaseTop Left X"+ jcuboid.getCuboid_pointBaseTopLeftX());
		   builder.add("Point BaseTop Left Y"+ jcuboid.getCuboid_pointBaseTopLeftY());
		   builder.add("Height"+ jcuboid.getCuboid_height());
		   builder.add("Width"+ jcuboid.getCuboid_width());
		   builder.add("Length"+ jcuboid.getCuboid_length());
		   builder.add("Rotation"+ jcuboid.getCuboid_rotation());
		   builder.add("Workspace"+ jcuboid.getCuboid_workspace());
	       	  
       JsonArray array= builder.build();
	   
        return array;
    }
    
    
    
 

}
