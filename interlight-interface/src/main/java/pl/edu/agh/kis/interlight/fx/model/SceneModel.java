package pl.edu.agh.kis.interlight.fx.model;

import pl.edu.agh.kis.interlight.fx.parts.BoundsAnchor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class SceneModel {
	
	private String name;

	private Double sceneLengthM;
	private Double sceneWidthM;
	
	private Room room;
	private ObservableList<SceneShape> shapes;
	private ObservableList<LightPoint> lightPoints;
	private ObservableList<LightSource> lightSources;
	
	public SceneModel() {
		shapes = FXCollections
				.observableArrayList();
		lightPoints = FXCollections
				.observableArrayList();
		lightSources = FXCollections
				.observableArrayList();
		room = new Room();
	}
	
	public void createBounds(Pane canvas) {
		canvas.getChildren().removeIf(p -> p instanceof BoundsAnchor);
		canvas.getChildren().removeIf(p -> p instanceof Label);
		if(getRoom().getSceneObject() != null) {
			canvas.getChildren().remove(getRoom().getSceneObject());
		}
		getRoom().setSceneObject(new Polygon());
		getRoom().getSceneObject().setId("borders");
		canvas.getChildren().add(getRoom().getSceneObject());
		getRoom().getSceneObject().toBack();
	}

	public ObservableList<SceneShape> getShapes() {
		return shapes;
	}

	public Polygon getRoomBounds() {
		return (Polygon) getRoom().getSceneObject();
	}

	public ObservableList<LightPoint> getLightPoints() {
		return lightPoints;
	}
	
	public ObservableList<LightSource> getLightSources() {
		return lightSources;
	}
	
	public Double getSceneLengthM() {
		return sceneLengthM;
	}

	public void setSceneLengthM(Double sceneLengthM) {
		this.sceneLengthM = sceneLengthM;
	}

	public Double getSceneWidthM() {
		return sceneWidthM;
	}

	public void setSceneWidthM(Double sceneWidthM) {
		this.sceneWidthM = sceneWidthM;
	}

	public Double getRoomHeightM() {
		return getRoom().getHeight();
	}

	public void setRoomHeightM(Double roomHeightM) {
		this.getRoom().setHeight(roomHeightM);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getRoom() {
		return room;
	}
}
