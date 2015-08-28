package example;

import java.io.IOException;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import pl.edu.agh.kis.intelight.schema.JSONSchema;

public class JSONSchematest {
	
	
	public static void main(String[] args) throws IOException, ProcessingException {
		
		String jsonToValidate = "{\"Cuboids:\":[{\"Length\":15.0,\"Point Base Top Left X\":11.0,\"Height\":13.0,\"Point Base Top Left Y\":12.0,\"Width\":14.0,\"Rotation\":18,\"Workspace\":true},{\"Length\":15.0,\"Point Base Top Left X\":11.0,\"Height\":13.0,\"Point Base Top Left Y\":12.0,\"Width\":14.0,\"Rotation\":18,\"Workspace\":true}],\"Light Points:\":[{\"X\":11.0,\"Y\":12.0,\"Height\":13.0,\"ID\":18},{\"X\":11.0,\"Y\":12.0,\"Height\":13.0,\"ID\":18}],\"Light Sources:\":[{\"IES\":\"1Scena\",\"Power\":11.0},{\"IES\":\"1Scena\",\"Power\":11.0},{\"IES\":\"1Scena\",\"Power\":11.0},{\"IES\":\"1Scena\",\"Power\":11.0}],\"Room points:\":[{\"X\":14.0,\"Y\":15.0},{\"X\":14.0,\"Y\":15.0}],\"Room height:\":13.0,\"Scene length:\":15.0,\"Name:\":\"1Scena\",\"Scene width:\":14.0,\"Cylinders:\":[{\"Radius Y\":17.0,\"Radius X\":16.0,\"Point Base Center X\":11.0,\"Point Base Center Y\":12.0,\"Height\":13.0,\"Rotation\":18,\"Workspace\":true},{\"Radius Y\":17.0,\"Radius X\":16.0,\"Point Base Center X\":11.0,\"Point Base Center Y\":12.0,\"Height\":13.0,\"Rotation\":18,\"Workspace\":true},{\"Radius Y\":17.0,\"Radius X\":16.0,\"Point Base Center X\":11.0,\"Point Base Center Y\":12.0,\"Height\":13.0,\"Rotation\":18,\"Workspace\":true}]}";
		boolean test = JSONSchema.validate(jsonToValidate);
			
		System.out.println(test);
		
	}
	

}
