package pl.edu.agh.kis.interlight.fx.model;

import javafx.scene.shape.Rectangle;

public class Cuboid extends AbstractSceneObject {
	
	private Rectangle rectangle;
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	@Override
	public String toString() {
		return "Cuboid...";
	}

}
