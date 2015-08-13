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



public class JLightPoint {
	
	
	private int id;
    private double lightPoint_x;
    private double lightPoint_y;
    private double lightPoint_height;
    //private double[][] lightPoints;
    
   
    public int getLightPoint_id() {
        return id;
    }
    
    public void setLightPoint_id(int id) {
        this.id = id;
    }
    
    public double getLightPoint_X() {
        return lightPoint_x;
    }
    
    public void setLightPoint_X(double lightPoint_x) {
        this.lightPoint_x = lightPoint_x;
    }
    
    public double getLightPoint_Y() {
        return lightPoint_y;
    }
    
    public void setLightPoint_Y(double lightPoint_y) {
        this.lightPoint_y = lightPoint_y;
    }
    
    public double getLightPoint_height() {
        return lightPoint_height;
    }
    
    public void setLightPoint_height(double lightPoint_height) {
        this.lightPoint_height = lightPoint_height;
    }
    
   /* 
	
	public double[][] getLight_points() {
		return lightPoints;
	}

	public void setLight_points(double[][] lightPoints) {
		this.lightPoints = lightPoints;
	}*/
	
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Light point\n");
        sb.append("Light Point id"+getLightPoint_id()+"\n");
        sb.append("Light Point x"+getLightPoint_X()+"\n");
        sb.append("Light Point y"+getLightPoint_Y()+"\n");
        sb.append("Light Point height"+getLightPoint_height()+"\n");
      //  sb.append("List light Point"+getLight_points()+"\n");
        sb.append("\n");

        return sb.toString();
    }
    
}