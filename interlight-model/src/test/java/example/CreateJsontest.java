package example;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.kis.interlight.model.JPoint;
import pl.edu.agh.kis.interlight.model.JSONCreator;
import pl.edu.agh.kis.interlight.model.JCuboid;
import pl.edu.agh.kis.interlight.model.JCylinder;
import pl.edu.agh.kis.interlight.model.JLightPoint;
import pl.edu.agh.kis.interlight.model.JLightSource;
import pl.edu.agh.kis.interlight.model.JScene;

public class CreateJsontest {
	
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
	
	
	JPoint jpoint = createPoint();
	List<JPoint> jpoints = new LinkedList<>();;
	jpoints.add(jpoint);
	jpoints.add(jpoint);
	jpoints.add(jpoint);
	jpoints.add(jpoint);
	
	
	JSONCreator json = new JSONCreator(scene, cuboids, cylinders, lightPoints, lightSources, jpoints);
	
	json.createAndWrite();
	
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
       // lightSource.setLightSource_dimming(23);
                
        return lightSource;
        
    }
    
    
    public static JPoint createPoint() {
    	 
        JPoint jpoint = new JPoint();
        jpoint.setPoint_X(45);
        jpoint.setPoint_Y(35);
                
        return jpoint;
        
    }
    
    
	
}