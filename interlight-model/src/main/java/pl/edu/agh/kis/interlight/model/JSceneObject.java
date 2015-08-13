package pl.edu.agh.kis.interlight.model;


import java.util.List;



public class JSceneObject {
	

	private List<JCuboid> cuboids;
	private List<JCylinder> cylinders;
	private List<JLightPoint> lightPoints;
	private List<JLightSource> lightSources;
	private List<JPoint> points;
	private JScene scene;
	
	
	
	public JSceneObject(List<JCuboid> cuboids, List<JCylinder> cylinders,
			List<JLightPoint> lightPoints, List<JLightSource> lightSources, 
			List<JPoint> points, JScene scene) {
		
		this.scene = scene;
		this.cuboids = cuboids;
		this.cylinders = cylinders;
		this.lightPoints = lightPoints;
		this.lightSources = lightSources;
		this.points = points;
		
		
	}
	

	public JScene getScene() {
		return scene;
	}

	public List<JCuboid> getCuboids() {
		return cuboids;
	}

	public List<JCylinder> getCylinders() {
		return cylinders;
	}

	public List<JLightPoint> getLightPoints() {
		return lightPoints;
	}

	public List<JLightSource> getLightSources() {
		return lightSources;
	}

	public List<JPoint> getPoints() {
		return points;
	}

}
