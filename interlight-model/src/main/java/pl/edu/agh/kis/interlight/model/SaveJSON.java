package pl.edu.agh.kis.interlight.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

public class SaveJSON {
	
	public static void main(String[] args) throws ProcessingException  {
		
		
	try {
		String JSON = JSONReadToString.readPrettyJson("1Scena_scene.json");
				
		boolean test = JSONSchema.validate(JSON);
		
		if(test){
			FileWriter file = new FileWriter("ASDFG_scene.json");
		   	 file.write(JSON);
		   	 file.flush();
		   	 file.close();
		}
		else System.out.println("B³¹d sk³adni");
		
   	 
   	 
   	 } catch (IOException e) {
   	 e.printStackTrace();
   	 }
	}
}
