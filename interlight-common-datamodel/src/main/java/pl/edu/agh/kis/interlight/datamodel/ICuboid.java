package pl.edu.agh.kis.interlight.datamodel;

import pl.edu.agh.kis.interlight.datamodel.util.IPoint;

/**
 * Cuboid defined by top-left point of the base, base width and length, height
 * (all in meters) and rotation (degrees, clockwise)
 */
public class ICuboid {

	private IPoint pointBaseTopLeft;
	private Double height;
	/**
	 * BASE width and length
	 */
	private Double width;
	private Double length;
	/**
	 * Clockwise rotation from the top-left base point (0-89)
	 */
	private Double rotation;

	public ICuboid(IPoint pointBaseTopLeft, Double height, Double width,
			Double length, Double rotation) {
		this.pointBaseTopLeft = pointBaseTopLeft;
		this.height = height;
		this.width = width;
		this.length = length;
		this.rotation = rotation;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getRotation() {
		return rotation;
	}

	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public IPoint getPointBaseTopLeft() {
		return pointBaseTopLeft;
	}

	public void setPointBaseTopLeft(IPoint pointBaseTopLeft) {
		this.pointBaseTopLeft = pointBaseTopLeft;
	}

}
