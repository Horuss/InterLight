package pl.edu.agh.kis.interlight.model;


import java.util.Arrays;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pl.edu.agh.kis.interlight.datamodel.util.IPoint;



public class JCuboid {
	
	
	private double cuboid_pointBaseTopLeftX;
	private double cuboid_pointBaseTopLeftY;
	private double cuboid_height;
	private double cuboid_width;
	private double cuboid_length;
	private int cuboid_rotation;
	private boolean cuboid_workspace;
	
	
	
	public double getCuboid_pointBaseTopLeftX() {
		return cuboid_pointBaseTopLeftX;
	}

	public void setCuboid_pointBaseTopLeftX(double cuboid_pointBaseTopLeftX) {
		this.cuboid_pointBaseTopLeftX = cuboid_pointBaseTopLeftX;
	}
	
	public double getCuboid_pointBaseTopLeftY() {
		return cuboid_pointBaseTopLeftY;
	}

	public void setCuboid_pointBaseTopLeftY(double cuboid_pointBaseTopLeftY) {
		this.cuboid_pointBaseTopLeftY = cuboid_pointBaseTopLeftY;
	}
	
	public double getCuboid_height() {
		return cuboid_height;
	}

	public void setCuboid_height(double cuboid_height) {
		this.cuboid_height = cuboid_height;
	}

	public double getCuboid_width() {
		return cuboid_width;
	}

	public void setCuboid_width(double cuboid_width) {
		this.cuboid_width = cuboid_width;
	}
	
	public double getCuboid_length() {
		return cuboid_length;
	}

	public void setCuboid_length(double cuboid_length) {
		this.cuboid_length = cuboid_length;
	}

	public int getCuboid_rotation() {
		return cuboid_rotation;
	}

	public void setCuboid_rotation(int cuboid_rotation) {
		this.cuboid_rotation = cuboid_rotation;
	}

	public boolean getCuboid_workspace() {
		return cuboid_workspace;
	}

	public void setCuboid_workspace(boolean cuboid_workspace) {
		this.cuboid_workspace = cuboid_workspace;
	}
	
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cuboid\n");
        sb.append("Cuboid Point BaseTop Left X"+getCuboid_pointBaseTopLeftX()+"\n");
        sb.append("Cuboid Point BaseTop Left Y"+getCuboid_pointBaseTopLeftY()+"\n");
        sb.append("Cuboid Height"+getCuboid_height()+"\n");
        sb.append("Cuboid Width"+getCuboid_width()+"\n");
        sb.append("Cuboid Length"+getCuboid_length()+"\n");
        sb.append("Cuboid Rotation"+getCuboid_rotation()+"\n");
        sb.append("Cuboid Workspace"+getCuboid_workspace()+"\n");
        sb.append("\n");
    
        return sb.toString();
    }
    
}