package pl.edu.agh.kis.interlight.datamodel;

import pl.edu.agh.kis.interlight.datamodel.util.IPoint;

/**
 * Light source defined by location point (x, y) and height (usually equals to
 * room's height) (all in meters).
 */
public class ILightPoint {

	private Integer id;
	private IPoint point;
	private Double height;

	public ILightPoint(Integer id, IPoint point, Double height) {
		this.id = id;
		this.point = point;
		this.height = height;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IPoint getPoint() {
		return point;
	}

	public void setPoint(IPoint point) {
		this.point = point;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "ILightPoint [id=" + id + ", point=" + point + ", height="
				+ height + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
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
		ILightPoint other = (ILightPoint) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}

}
