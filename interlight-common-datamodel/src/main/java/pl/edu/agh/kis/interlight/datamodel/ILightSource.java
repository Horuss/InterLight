package pl.edu.agh.kis.interlight.datamodel;

public class ILightSource {

	private String ies;
	private String power;

	public ILightSource(String ies, String power) {
		this.ies = ies;
		this.power = power;
	}

	@Override
	public String toString() {
		return "Light Source [ies: " + ies + ", power: " + power + "]";
	}

}
