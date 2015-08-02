package pl.edu.agh.kis.interlight.datamodel;

public class ILightSource {

	private String ies;
	private Double power;

	public ILightSource(String ies, Double power) {
		this.ies = ies;
		this.power = power;
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

	@Override
	public String toString() {
		return "Light Source [ies: " + ies + ", power: " + power + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
