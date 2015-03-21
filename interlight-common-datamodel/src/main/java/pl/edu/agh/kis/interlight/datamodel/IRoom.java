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

}
