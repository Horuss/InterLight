package pl.edu.agh.kis.interlight.ies;

public class IesProfile {

	private String name;
	private double power;
	private double[] verticalAngles;
	private double[] horizontalAngles;
	private double[][] lumenValues;

	public IesProfile(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double[] getVerticalAngles() {
		return verticalAngles;
	}

	public void setVerticalAngles(double[] verticalAngles) {
		this.verticalAngles = verticalAngles;
	}

	public double[] getHorizontalAngles() {
		return horizontalAngles;
	}

	public void setHorizontalAngles(double[] horizontalAngles) {
		this.horizontalAngles = horizontalAngles;
	}

	public double[][] getLumenValues() {
		return lumenValues;
	}

	public void setLumenValues(double[][] lumenValues) {
		this.lumenValues = lumenValues;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
