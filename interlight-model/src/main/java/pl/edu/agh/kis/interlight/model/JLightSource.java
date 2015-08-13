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



public class JLightSource {
	
	
    private String lightSource_ies;
	private double lightSource_power;
	//private int lightSource_dimming;
    
    
   
	public String getLightSource_ies() {
        return lightSource_ies;
    }
    
    public void setLightSource_ies(String lightSource_ies) {
        this.lightSource_ies = lightSource_ies;
    }
    
    public double getLightSource_power() {
        return lightSource_power;
    }
    
    public void setLightSource_power(double lightSource_power) {
        this.lightSource_power = lightSource_power;
    }
    
  /*  public int getLightSource_dimming() {
        return lightSource_dimming;
    }
    
    public void setLightSource_dimming(int lightSource_dimming) {
        this.lightSource_dimming = lightSource_dimming;
    }*/
	
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Light source\n");
        sb.append("Ies"+getLightSource_ies()+"\n");
        sb.append("Power"+getLightSource_power()+"\n");
        //sb.append("Dimming"+getLightSource_dimming()+"\n");
        sb.append("\n");

        return sb.toString();
    }
    
}