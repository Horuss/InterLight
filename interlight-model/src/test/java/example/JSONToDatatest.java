package example;

import java.io.IOException;

import pl.edu.agh.kis.interlight.datamodel.sets.ISceneSet;
import pl.edu.agh.kis.interlight.model.JSONToData;


public class JSONToDatatest {
	

	
	public static void main(String[] args) throws IOException {
		
	
	ISceneSet scene = JSONToData.main("C:/Users/Bartek/Interlight1/InterLight/interlight-model/1Scena_scene.json");

		//ISceneSet scene = JSONToData.main("Scenaaaa_scene.json");
		
	System.out.println(scene);
	
}
}