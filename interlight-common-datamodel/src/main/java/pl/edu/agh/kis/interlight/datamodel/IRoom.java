package pl.edu.agh.kis.interlight.datamodel;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.kis.interlight.datamodel.util.IPoint;

/**
 * Room defined by list of points of the base and height (all in meters).
 */
public class IRoom {

	private Double height;
	private List<IPoint> points;

	public IRoom(Double height) {
		this.height = height;
		this.points = new LinkedList<IPoint>();
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public List<IPoint> getPoints() {
		return points;
	}

	public void setPoints(List<IPoint> points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "IRoom [height=" + height + ", points=" + points + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
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
		IRoom other = (IRoom) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		return true;
	}

}
