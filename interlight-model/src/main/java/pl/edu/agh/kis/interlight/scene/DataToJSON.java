package pl.edu.agh.kis.interlight.scene;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.kis.interlight.datamodel.ICuboid;
import pl.edu.agh.kis.interlight.datamodel.ICylinder;
import pl.edu.agh.kis.interlight.datamodel.ILightPoint;
import pl.edu.agh.kis.interlight.datamodel.ILightSource;
import pl.edu.agh.kis.interlight.datamodel.IRoom;
import pl.edu.agh.kis.interlight.datamodel.IScene;
import pl.edu.agh.kis.interlight.datamodel.util.IPoint;
import pl.edu.agh.kis.interlight.model.JCuboid;
import pl.edu.agh.kis.interlight.model.JCylinder;
import pl.edu.agh.kis.interlight.model.JLightPoint;
import pl.edu.agh.kis.interlight.model.JLightSource;
import pl.edu.agh.kis.interlight.model.JPoint;
import pl.edu.agh.kis.interlight.model.JScene;

public class DataToJSON {
	
	
	private List<ICuboid> cuboids = new LinkedList<>();
	private List<ICylinder> cylinders = new LinkedList<>();
	private List<ILightPoint> lightPoints = new LinkedList<>();
	private List<ILightSource> lightSources = new LinkedList<>();
	private IRoom room;
	private IScene scene;
	
		
	
	public DataToJSON(List<ICuboid> cuboids, List<ICylinder> cylinders, List<ILightPoint> lightPoints,
			List<ILightSource> lightSources, IRoom room, IScene scene){
		
		this.cuboids = cuboids;
		this.cylinders = cylinders;
		this.lightPoints = lightPoints;
		this.lightSources = lightSources;
		this.room = room;
		this.scene = scene;
		
		
	}
	
	
	public void transformAndGenerate(){
		
		JScene jscene = new JScene();
		List<JCuboid> jcuboids = new LinkedList<>();
		List<JCylinder> jcylinders = new LinkedList<>();
		List<JLightPoint> jlightPoints = new LinkedList<>();
		List<JLightSource> jlightSources = new LinkedList<>();
		List<JPoint> jpoints = new LinkedList<>();
		
		
		jscene.setScene_name(scene.getName());
		jscene.setScene_width(scene.getWidth());
        jscene.setScene_length(scene.getLength());
        jscene.setRoom_height(room.getHeight());
        
        JCuboid jcuboid = new JCuboid();
        for(ICuboid cuboid : cuboids)  {
        	
        	jcuboid.setCuboid_pointBaseTopLeftX(cuboid.getPointBaseTopLeft().getX());
            jcuboid.setCuboid_pointBaseTopLeftY(cuboid.getPointBaseTopLeft().getY());
            jcuboid.setCuboid_height(cuboid.getHeight());
            jcuboid.setCuboid_width(cuboid.getWidth());
            jcuboid.setCuboid_length(cuboid.getLength());
            jcuboid.setCuboid_rotation(cuboid.getRotation());
            jcuboid.setCuboid_workspace(cuboid.getWorkspace());
            
            jcuboids.add(jcuboid);
        	
        }
        
        JCylinder jcylinder = new JCylinder();
        for(ICylinder cylinder : cylinders)  {
        	
        	jcylinder.setCylinder_pointBaseCenterX(cylinder.getPointBaseCenter().getX());
        	jcylinder.setCylinder_pointBaseCenterY(cylinder.getPointBaseCenter().getY());
        	jcylinder.setCylinder_radiusX(cylinder.getRadiusX());
        	jcylinder.setCylinder_radiusY(cylinder.getRadiusY());
        	jcylinder.setCylinder_height(cylinder.getHeight());
        	jcylinder.setCylinder_rotation(cylinder.getRotation());
        	jcylinder.setCylinder_workspace(cylinder.getWorkspace());
            
            jcylinders.add(jcylinder);
        	
        }
        
        
        JLightPoint jlightPoint = new JLightPoint();
        for(ILightPoint lightPoint : lightPoints)  {
        	
        	jlightPoint.setLightPoint_id(lightPoint.getId());
        	jlightPoint.setLightPoint_X(lightPoint.getPoint().getX());
        	jlightPoint.setLightPoint_Y(lightPoint.getPoint().getY());
        	jlightPoint.setLightPoint_height(lightPoint.getHeight());
        	            
        	jlightPoints.add(jlightPoint);
        	
        }
        
        
        JLightSource jlightSource = new JLightSource();
        for(ILightSource lightSource : lightSources)  {
        	
        	jlightSource.setLightSource_ies(lightSource.getIes());
        	jlightSource.setLightSource_power(lightSource.getPower());
        	//jlightSource.setLightSource_dimming(lightSource.getDimming());
        	            
        	jlightSources.add(jlightSource);
        	
        }
        
        
        
        for(IPoint point : room.getPoints())  {
        	JPoint jpoint = new JPoint();
        	jpoint.setPoint_X(point.getX());
        	jpoint.setPoint_Y(point.getY());
        	            
        	jpoints.add(jpoint);
        	
        }  
        
        
        
        
        JSONCreator json = new JSONCreator(jscene, jcuboids, jcylinders, jlightPoints, jlightSources, jpoints);
    	
    	json.createAndWrite();
        
		
	}

}
