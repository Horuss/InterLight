package pl.edu.agh.kis.interlight.fx.model;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.CuboidPropertiesPanel;

public class Cuboid extends SceneShape {

	private static int nextId = 1;

	private Rectangle rectangle;

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public String toString() {
		return "Cuboid " + getId();
	}

	@Override
	public void createPropertiesPanel(GuiHelper guiHelper) {
		propertiesPanel = new CuboidPropertiesPanel(this, guiHelper);
	}

	@Override
	public Shape getSceneObject() {
		return rectangle;
	}

	@Override
	public int getNextId() {
		return nextId++;
	}

	@Override
	protected boolean canTranslateX(Double newTranslateX) {
		if (newTranslateX < 0) {
			return false;
		}
		if (newTranslateX + getRectangle().getWidth() > GuiHelper.CANVAS_WIDTH) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean canTranslateY(Double newTranslateY) {
		if (newTranslateY < 0) {
			return false;
		}
		if (newTranslateY + getRectangle().getHeight() > GuiHelper.CANVAS_HEIGHT) {
			return false;
		}
		return true;
	}

}
