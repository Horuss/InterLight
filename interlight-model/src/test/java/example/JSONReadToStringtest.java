package example;

import pl.edu.agh.kis.interlight.scene.JSONReadToString;


public class JSONReadToStringtest {
	
	public static void main(String[] args)  {
		
		
		String JSON = JSONReadToString.readPrettyJson("1Scena_scene.json");
			
		System.out.println(JSON);
		
	}

}
