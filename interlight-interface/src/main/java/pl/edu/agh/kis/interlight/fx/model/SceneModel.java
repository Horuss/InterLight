package pl.edu.agh.kis.interlight.fx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.kis.interlight.datamodel.Room;

public class SceneModel {

	private Room room;
	private ObservableList<AbstractSceneObject> objects = FXCollections
			.observableArrayList();

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ObservableList<AbstractSceneObject> getObjects() {
		return objects;
	}

	public void setObjects(ObservableList<AbstractSceneObject> objects) {
		this.objects = objects;
	}

}
