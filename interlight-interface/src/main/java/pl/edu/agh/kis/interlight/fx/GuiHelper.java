package pl.edu.agh.kis.interlight.fx;

import pl.edu.agh.kis.interlight.datamodel.Room;

public class GuiHelper {

	private Double sceneLength;
	private Double sceneWidth;

	public GuiHelper(Room room, Double maxHeight, Double maxWidth) {
		Double yScale = maxHeight / room.getLength();
		Double xScale = maxWidth / room.getWidth();
		Double scale = Math.min(xScale, yScale);
		sceneWidth = scale * room.getWidth();
		sceneLength = scale * room.getLength();
	}

	public Double getSceneWidth() {
		return sceneWidth;
	}

	public Double getSceneLength() {
		return sceneLength;
	}
}
