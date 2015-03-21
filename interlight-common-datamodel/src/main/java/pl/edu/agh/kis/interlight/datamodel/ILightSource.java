package pl.edu.agh.kis.interlight.datamodel;

import pl.edu.agh.kis.interlight.datamodel.util.IPoint;

/**
 * Light source defined by location point (x, y), height (usually equals to
 * room's height) (all in meters) IES file, power (W) and dimming (% 0-100).
 */
public class ILightSource {

	private IPoint point;
	private Double height;
	private String ies;
	private Double power;
	private Integer dimming;

	public ILightSource(IPoint point, Double height, String ies, Double power,
			Integer dimming) {
		this.point = point;
		this.height = height;
		this.ies = ies;
		this.power = power;
		this.dimming = dimming;
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

	public String getIes() {
		return ies;
	}

	public void setIes(String ies) {
		this.ies = ies;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public Integer getDimming() {
		return dimming;
	}

	public void setDimming(Integer dimming) {
		this.dimming = dimming;
	}

}
