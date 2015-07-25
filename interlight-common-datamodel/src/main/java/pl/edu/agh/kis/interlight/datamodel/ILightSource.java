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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dimming == null) ? 0 : dimming.hashCode());
		result = prime * result + ((ies == null) ? 0 : ies.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
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
		ILightSource other = (ILightSource) obj;
		if (dimming == null) {
			if (other.dimming != null)
				return false;
		} else if (!dimming.equals(other.dimming))
			return false;
		if (ies == null) {
			if (other.ies != null)
				return false;
		} else if (!ies.equals(other.ies))
			return false;
		if (power == null) {
			if (other.power != null)
				return false;
		} else if (!power.equals(other.power))
			return false;
		return true;
	}

}
