package pl.edu.agh.kis.interlight.fx.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.LightSourcePropertiesPanel;

public class LightSource extends AbstractSceneObject {

	private static int nextId = 1;

	private Circle circle;
	private Double power;
	
	public LightSource(Double height) {
		super();
		this.setHeight(height);
		Circle c = new Circle();
		c.setFill(Color.GOLD.deriveColor(1, 1, 1, 0.5));
		c.setStroke(Color.GOLD);
		c.setStrokeWidth(1);
		c.setStrokeType(StrokeType.OUTSIDE);
		c.getStyleClass().add("lightSrc");
		c.setLayoutX((0.5 + this.getId() / 10.0) * GuiHelper.SCALE_M_TO_PX);
		c.setLayoutY((0.5 + this.getId() / 10.0) * GuiHelper.SCALE_M_TO_PX);
		c.setRadius(6.0);
		this.setCircle(c);
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	@Override
	public String toString() {
		return "LightSource " + getId();
	}

	@Override
	public void createPropertiesPanel(GuiHelper guiHelper) {
		propertiesPanel = new LightSourcePropertiesPanel(this, guiHelper);
	}

	@Override
	public Shape getSceneObject() {
		return circle;
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
		if (newTranslateX > GuiHelper.CANVAS_WIDTH) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean canTranslateY(Double newTranslateY) {
		if (newTranslateY < 0) {
			return false;
		}
		if (newTranslateY > GuiHelper.CANVAS_HEIGHT) {
			return false;
		}
		return true;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

}
