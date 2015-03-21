package pl.edu.agh.kis.interlight.datamodel.util;

/**
 * Point defined by coordinates x and y in meters
 */
public class IPoint {

	private Double x;
	private Double y;

	public IPoint(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

}
