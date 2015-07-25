package pl.edu.agh.kis.interlight.datamodel;

import pl.edu.agh.kis.interlight.datamodel.util.IPoint;

/**
 * Cylinder defined by center point of the base, base radiusX and radius Y,
 * height (all in meters) and rotation (degrees, clockwise)
 */
public class ICylinder {

	private IPoint pointBaseCenter;
	private Double radiusX;
	private Double radiusY;
	private Double height;
	/**
	 * Clockwise rotation from the center base point (0-89)
	 */
	private Integer rotation;
	private Boolean workspace;

	public ICylinder(IPoint pointBaseCenter, Double radiusX, Double radiusY,
			Double height, Integer rotation, Boolean workspace) {
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

	public Integer getRotation() {
		return rotation;
	}

	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	public Boolean getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Boolean workspace) {
		this.workspace = workspace;
	}

	@Override
	public String toString() {
		return "ICylinder [pointBaseCenter=" + pointBaseCenter + ", radiusX="
				+ radiusX + ", radiusY=" + radiusY + ", height=" + height
				+ ", rotation=" + rotation + ", workspace=" + workspace + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result
				+ ((pointBaseCenter == null) ? 0 : pointBaseCenter.hashCode());
		result = prime * result + ((radiusX == null) ? 0 : radiusX.hashCode());
		result = prime * result + ((radiusY == null) ? 0 : radiusY.hashCode());
		result = prime * result
				+ ((rotation == null) ? 0 : rotation.hashCode());
		result = prime * result
				+ ((workspace == null) ? 0 : workspace.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ICylinder other = (ICylinder) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (pointBaseCenter == null) {
			if (other.pointBaseCenter != null)
				return false;
		} else if (!pointBaseCenter.equals(other.pointBaseCenter))
			return false;
		if (radiusX == null) {
			if (other.radiusX != null)
				return false;
		} else if (!radiusX.equals(other.radiusX))
			return false;
		if (radiusY == null) {
			if (other.radiusY != null)
				return false;
		} else if (!radiusY.equals(other.radiusY))
			return false;
		if (rotation == null) {
			if (other.rotation != null)
				return false;
		} else if (!rotation.equals(other.rotation))
			return false;
		if (workspace == null) {
			if (other.workspace != null)
				return false;
		} else if (!workspace.equals(other.workspace))
			return false;
		return true;
	}

}
