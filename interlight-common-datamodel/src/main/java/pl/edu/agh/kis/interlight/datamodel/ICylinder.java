package pl.edu.agh.kis.interlight.datamodel;

import pl.edu.agh.kis.interlight.datamodel.util.IPoint;

/**
 * Cylinder defined by center point of the base, base radiusX and radius Y, height
 * (all in meters) and rotation (degrees, clockwise)
 */
public class ICylinder {

	private IPoint pointBaseCenter;
	private Double radiusX;
	private Double radiusY;
	private Double height;
	/**
	 * Clockwise rotation from the center base point (0-89)
	 */
	private Double rotation;
	private Boolean workspace;

	public ICylinder(IPoint pointBaseCenter, Double radiusX, Double radiusY,
			Double height, Double rotation, Boolean workspace) {
		this.pointBaseCenter = pointBaseCenter;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
		this.height = height;
		this.rotation = rotation;
		this.setWorkspace(workspace);
	}

	public IPoint getPointBaseCenter() {
		return pointBaseCenter;
	}

	public void setPointBaseCenter(IPoint pointBaseCenter) {
		this.pointBaseCenter = pointBaseCenter;
	}

	public Double getRadiusX() {
		return radiusX;
	}

	public void setRadiusX(Double radiusX) {
		this.radiusX = radiusX;
	}

	public Double getRadiusY() {
		return radiusY;
	}

	public void setRadiusY(Double radiusY) {
		this.radiusY = radiusY;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getRotation() {
		return rotation;
	}

	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	public Boolean getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Boolean workspace) {
		this.workspace = workspace;
	}
}
