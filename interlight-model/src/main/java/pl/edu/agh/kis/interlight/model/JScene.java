package pl.edu.agh.kis.interlight.model;

import java.util.Arrays;
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



public class JScene {
	
	
	private String scene_name;
	private double scene_width;
	private double scene_length;
    private double room_height;
    
    
   
    public String getScene_name() {
        return scene_name;
    }
    
    public void setScene_name(String scene_name) {
        this.scene_name = scene_name;
    }
    
    public double getScene_width() {
        return scene_width;
    }
    
    public void setScene_width(double scene_width) {
        this.scene_width = scene_width;
    }
    
    public double getScene_length() {
        return scene_length;
    }
    
    public void setScene_length(double scene_length) {
        this.scene_length = scene_length;
    }
    
    public double getRoom_height() {
        return room_height;
    }
    
    public void setRoom_height(double room_height) {
        this.room_height = room_height;
    }
 
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Scene\n");
        sb.append("Name"+getScene_name()+"\n");
        sb.append("Scene width"+getScene_width()+"\n");
        sb.append("Scene length"+getScene_length()+"\n");
        sb.append("Scene height"+getRoom_height()+"\n");
        sb.append("\n");
         
        return sb.toString();
    }
    
}