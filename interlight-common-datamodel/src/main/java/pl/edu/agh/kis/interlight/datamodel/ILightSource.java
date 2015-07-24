package pl.edu.agh.kis.interlight.datamodel;

public class ILightSource {

	private String ies;
	private Integer power;
	/**
	 * Percentage in natural numbers (0-100)
	 */
	private Integer dimming;

	public ILightSource(String ies, Integer power, Integer dimming) {
		this.ies = ies;
		this.power = power;
		this.dimming = dimming;
	}

	public String getIes() {
		return ies;
	}

	public void setIes(String ies) {
		this.ies = ies;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getDimming() {
		return dimming;
	}

	public void setDimming(Integer dimming) {
		this.dimming = dimming;
	}

	@Override
	public String toString() {
		return "Light Source [ies: " + ies + ", power: " + power
				+ ", dimming: " + dimming + "]";
	}

}
