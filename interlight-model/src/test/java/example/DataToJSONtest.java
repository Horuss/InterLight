package example;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.kis.interlight.datamodel.ICuboid;
import pl.edu.agh.kis.interlight.datamodel.ICylinder;
import pl.edu.agh.kis.interlight.datamodel.ILightPoint;
import pl.edu.agh.kis.interlight.datamodel.ILightSource;
import pl.edu.agh.kis.interlight.datamodel.IRoom;
import pl.edu.agh.kis.interlight.datamodel.IScene;
import pl.edu.agh.kis.interlight.datamodel.util.IPoint;
import pl.edu.agh.kis.interlight.model.JCuboid;
import pl.edu.agh.kis.interlight.model.JCylinder;
import pl.edu.agh.kis.interlight.model.JLightPoint;
import pl.edu.agh.kis.interlight.model.JLightSource;
import pl.edu.agh.kis.interlight.model.JPoint;
import pl.edu.agh.kis.interlight.model.JScene;
import pl.edu.agh.kis.interlight.scene.DataToJSON;
import pl.edu.agh.kis.interlight.scene.JSONCreator;

public class DataToJSONtest {

	
	public static void main(String[] args) throws IOException {
    	
		
		double X = 11.0;
		double Y = 12.0;
		double H = 13.0;
		double W = 14.0;
		double L = 15.0;
		double RX = 16.0;
		double RY = 17.0;
		int R = 18;
		boolean WS = true;
		String S = "1Scena";
		int P = 19;
		int D = 110;
		
		
		
		
		
		
		ICuboid iCuboid = new ICuboid(new IPoint(X, Y), H, W, L, R, WS);
		
		List<ICuboid> cuboids = new LinkedList<>();
		cuboids.add(iCuboid);
		cuboids.add(iCuboid);
		

		System.out.println(cuboids);
		 
		
		
		ICylinder iCylinder = new ICylinder(new IPoint(X, Y), RX, RY, H, R, WS);
		List<ICylinder> cylinders = new LinkedList<>();
		cylinders.add(iCylinder);
		cylinders.add(iCylinder);
		cylinders.add(iCylinder);
		
		
		
		ILightPoint iLightPoint = new ILightPoint(R, new IPoint(X, Y), H);
		List<ILightPoint> lightPoints = new LinkedList<>();
		lightPoints.add(iLightPoint);
		lightPoints.add(iLightPoint);
		
		
		
		ILightSource iLightSource = new ILightSource(S, X);
		List<ILightSource> lightSources = new LinkedList<>();
		lightSources.add(iLightSource);
		lightSources.add(iLightSource);
		lightSources.add(iLightSource);
		lightSources.add(iLightSource);
		
		
		IRoom iRoom = new IRoom(H, Arrays.asList(new IPoint(X, Y), new IPoint(W, L)));
		iRoom.setPoints(Arrays.asList(new IPoint(X, Y), new IPoint(W, L)));
		
		
		
		IScene iScene = new IScene(S, W, L);
		
		
		
		
		
		
		
		
		DataToJSON data = new DataToJSON(cuboids, cylinders, lightPoints, lightSources, iRoom, iScene);
		
		data.transformAndGenerate();
		
	}



	    
	
	
}
