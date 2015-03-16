package pl.edu.agh.kis.interlight.fx.model;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.RoomPropertiesPanel;

public class Room extends AbstractSceneObject {

	private Polygon roomBounds;
	
	public Room() {
		super();
		
	}
	
	public void setSceneObject(Polygon polygon) {
		roomBounds = polygon;
	}
	
	@Override
	public Shape getSceneObject() {
		return roomBounds;
	}

	@Override
	public void createPropertiesPanel(GuiHelper guiHelper) {
		propertiesPanel = new RoomPropertiesPanel(this, guiHelper);
	}

	@Override
	public int getNextId() {
		return 0;
	}

	@Override
	protected boolean canTranslateX(Double newTranslateX) {
		return false;
	}

	@Override
	protected boolean canTranslateY(Double newTranslateY) {
		return false;
	}

}
