package pl.edu.agh.kis.interlight.datamodel;

public class Room {

	private Double width;
	private Double length;
	private Double height;
	
	public Room(Double width, Double length, Double height) {
		this.width = width;
		this.length = length;
		this.height = height;
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
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	
}
