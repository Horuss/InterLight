package pl.edu.agh.kis.interlight.fx.model;

import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.LightPointPropertiesPanel;

public class LightPoint extends AbstractSceneObject {

	private static int nextId = 1;

	private Circle circle;

	public LightPoint(Double height) {
		this(height, null, null);
	}

	public LightPoint(Double height, Double x, Double y) {
		super();
		this.setHeight(height);
		Circle c = new Circle();
		c.setFill(Color.GOLD.deriveColor(1, 1, 1, 0.5));
		c.setStroke(Color.GOLD);
		c.setStrokeWidth(1);
		c.setStrokeType(StrokeType.OUTSIDE);
		c.getStyleClass().add("lightSrc");
		c.setLayoutX(Math.min((x == null ? (0.5 + getId() / 10.0) : x)
				* GuiHelper.SCALE_M_TO_PX, GuiHelper.CANVAS_WIDTH));
		c.setLayoutY(Math.min((y == null ? (0.5 + getId() / 10.0) : y)
				* GuiHelper.SCALE_M_TO_PX, GuiHelper.CANVAS_HEIGHT));
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
		return "LightPoint " + getId();
	}

	@Override
	public void createPropertiesPanel(GuiHelper guiHelper) {
		propertiesPanel = new LightPointPropertiesPanel(this, guiHelper);
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

	@SuppressWarnings("unchecked")
	@Override
	protected void selectMe(ListView<? extends AbstractSceneObject> listView) {
		((ListView<LightPoint>)listView).getSelectionModel().select(this);
	}

}
