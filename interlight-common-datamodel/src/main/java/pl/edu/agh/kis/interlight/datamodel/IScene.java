package pl.edu.agh.kis.interlight.datamodel;

public class IScene {

	private String name;
	private Double width;
	private Double length;

	public IScene(String name, Double width, Double length) {
		this.name = name;
		this.width = width;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

}
