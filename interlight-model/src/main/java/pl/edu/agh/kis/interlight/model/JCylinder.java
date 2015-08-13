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



public class JCylinder {
	
	
	private double cylinder_pointBaseCenterX;
	private double cylinder_pointBaseCenterY;
	private double cylinder_radiusX;
	private double cylinder_radiusY;
	private double cylinder_height;
	private int cylinder_rotation;
	private boolean cylinder_workspace;


	public double getCylinder_pointBaseCenterX() {
		return cylinder_pointBaseCenterX;
	}

	public void setCylinder_pointBaseCenterX(double cylinder_pointBaseCenterX) {
		this.cylinder_pointBaseCenterX = cylinder_pointBaseCenterX;
	}
	
	public double getCylinder_pointBaseCenterY() {
		return cylinder_pointBaseCenterY;
	}

	public void setCylinder_pointBaseCenterY(double cylinder_pointBaseCenterY) {
		this.cylinder_pointBaseCenterY = cylinder_pointBaseCenterY;
	}
	
	public double getCylinder_radiusX() {
		return cylinder_radiusX;
	}

	public void setCylinder_radiusX(double cylinder_radiusX) {
		this.cylinder_radiusX = cylinder_radiusX;
	}

	public double getCylinder_radiusY() {
		return cylinder_radiusY;
	}

	public void setCylinder_radiusY(double cylinder_radiusY) {
		this.cylinder_radiusY = cylinder_radiusY;
	}

	public double getCylinder_height() {
		return cylinder_height;
	}

	public void setCylinder_height(double cylinder_height) {
		this.cylinder_height = cylinder_height;
	}

	public int getCylinder_rotation() {
		return cylinder_rotation;
	}

	public void setCylinder_rotation(int cylinder_rotation) {
		this.cylinder_rotation = cylinder_rotation;
	}

	public boolean getCylinder_workspace() {
		return cylinder_workspace;
	}

	public void setCylinder_workspace(boolean cylinder_workspace) {
		this.cylinder_workspace = cylinder_workspace;
	}
	
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cylinder\n");
        sb.append("Cylinder Point Base Center"+getCylinder_pointBaseCenterX()+"\n");
        sb.append("Cylinder Point Base Center"+getCylinder_pointBaseCenterY()+"\n");
        sb.append("Cylinder cylinder radius X"+getCylinder_radiusX()+"\n");
        sb.append("Cylinder cylinder radius Y"+getCylinder_radiusY()+"\n");
        sb.append("Cylinder cylinder height"+getCylinder_height()+"\n");
        sb.append("Cylinder Rotation"+getCylinder_rotation()+"\n");
        sb.append("Cylinder Workspace"+getCylinder_workspace()+"\n");
        sb.append("\n");
     
        return sb.toString();
    }
    
}