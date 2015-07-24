package pl.edu.agh.kis.interlight.fx.model;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.CylinderPropertiesPanel;

public class Cylinder extends SceneShape {

	private static int nextId = 1;

	private Ellipse ellipse;

	public Ellipse getEllipse() {
		return ellipse;
	}

	public void setEllipse(Ellipse ellipse) {
		this.ellipse = ellipse;
	}

	@Override
	public String toString() {
		return "Cylinder " + getId();
	}

	@Override
	public void createPropertiesPanel(GuiHelper guiHelper) {
		propertiesPanel = new CylinderPropertiesPanel(this, guiHelper);
	}

	@Override
	public Shape getSceneObject() {
		return ellipse;
	}

	@Override
	public int getNextId() {
		return nextId++;
	}

	@Override
	protected boolean canTranslateX(Double newTranslateX) {
		if (newTranslateX - getEllipse().getRadiusX() < 0) {
			return false;
		}
		if (newTranslateX + getEllipse().getRadiusX() > GuiHelper.CANVAS_WIDTH) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean canTranslateY(Double newTranslateY) {
		if (newTranslateY - getEllipse().getRadiusY() < 0) {
			return false;
		}
		if (newTranslateY + getEllipse().getRadiusY() > GuiHelper.CANVAS_HEIGHT) {
			return false;
		}
		return true;
	}

}
