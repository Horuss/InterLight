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
	 * Clockwise rotation from the center base point (0-89)
	 */
	private Integer rotation;
	private Boolean workspace;

	public ICuboid(IPoint pointBaseTopLeft, Double height, Double width,
			Double length, Integer rotation, Boolean workspace) {
		this.pointBaseTopLeft = pointBaseTopLeft;
		this.height = height;
		this.width = width;
		this.length = length;
		this.rotation = rotation;
		this.workspace = workspace;
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

	public Integer getRotation() {
		return rotation;
	}

	public void setRotation(Integer rotation) {
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

	public Boolean getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Boolean workspace) {
		this.workspace = workspace;
	}

	@Override
	public String toString() {
		return "ICuboid [pointBaseTopLeft=" + pointBaseTopLeft + ", height="
				+ height + ", width=" + width + ", length=" + length
				+ ", rotation=" + rotation + ", workspace=" + workspace + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime
				* result
				+ ((pointBaseTopLeft == null) ? 0 : pointBaseTopLeft.hashCode());
		result = prime * result
				+ ((rotation == null) ? 0 : rotation.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		ICuboid other = (ICuboid) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (pointBaseTopLeft == null) {
			if (other.pointBaseTopLeft != null)
				return false;
		} else if (!pointBaseTopLeft.equals(other.pointBaseTopLeft))
			return false;
		if (rotation == null) {
			if (other.rotation != null)
				return false;
		} else if (!rotation.equals(other.rotation))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		if (workspace == null) {
			if (other.workspace != null)
				return false;
		} else if (!workspace.equals(other.workspace))
			return false;
		return true;
	}

}
