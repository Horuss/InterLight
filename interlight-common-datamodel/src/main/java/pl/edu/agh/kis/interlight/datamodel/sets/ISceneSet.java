package pl.edu.agh.kis.interlight.datamodel.sets;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.kis.interlight.datamodel.ICuboid;
import pl.edu.agh.kis.interlight.datamodel.ICylinder;
import pl.edu.agh.kis.interlight.datamodel.ILightPoint;
import pl.edu.agh.kis.interlight.datamodel.ILightSource;
import pl.edu.agh.kis.interlight.datamodel.IRoom;
import pl.edu.agh.kis.interlight.datamodel.IScene;

public class ISceneSet {

	private List<ICuboid> cuboids = new LinkedList<>();
	private List<ICylinder> cylinders = new LinkedList<>();
	private List<ILightPoint> lightPoints = new LinkedList<>();
	private List<ILightSource> lightSources = new LinkedList<>();
	private IRoom room;
	private IScene scene;

	public IScene getScene() {
		return scene;
	}

	public void setScene(IScene scene) {
		this.scene = scene;
	}

	public IRoom getRoom() {
		return room;
	}

	public void setRoom(IRoom room) {
		this.room = room;
	}

	public List<ICuboid> getCuboids() {
		return cuboids;
	}

	public List<ICylinder> getCylinders() {
		return cylinders;
	}

	public List<ILightPoint> getLightPoints() {
		return lightPoints;
	}

	public List<ILightSource> getLightSources() {
		return lightSources;
	}

}
