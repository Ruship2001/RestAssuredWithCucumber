package Utility;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader  {
	
	String URI;
	public String getURI() {
		JSONParser jParser= new JSONParser();
	
	
	try(FileReader file= new FileReader("C:\\RestAssured\\src\\test\\java\\JsonFile\\Config.Json")){
		Object obj = jParser.parse(file);
		JSONObject jObject= (JSONObject)obj;
		URI = (String) jObject.get("URI");
		
	}
	catch (Exception e) {
		 e.printStackTrace();
	}
	return URI;
	}
	

}
