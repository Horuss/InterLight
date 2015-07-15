package pl.edu.agh.kis.interlight.datamodel;

import pl.edu.agh.kis.interlight.datamodel.util.IPoint;

/**
 * Light source defined by location point (x, y) and height (usually equals to
 * room's height) (all in meters).
 */
public class ILightPoint {

	private IPoint point;
	private Double height;

	public ILightPoint(IPoint point, Double height) {
		this.point = point;
		this.height = height;
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
		return "Light Point [" + point + ", height: " + height + "]";
	}

}
