import io.restassured.path.json.JsonPath;

public class ReUseableMethods {
	
	public static JsonPath rowtoJson(String response) {
		
		JsonPath j = new JsonPath(response);
		return j;
		
	}
	

}
