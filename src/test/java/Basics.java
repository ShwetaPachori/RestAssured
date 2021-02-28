import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {



	public static void main(String[] args) {

		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","Application/json")
		.body("{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"").when().log().all().post("maps/api/place/add/json").then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).extract().response().asString();
		
		System.out.println("response is "+response);
		
		JsonPath js = new JsonPath(response);
		String place_id =js.getString("place_id");
		
		System.out.println("place_id is "+ js.getString("place_id"));
		
		
// This is next request
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","Application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+ place_id +"\",\r\n" + 
				"\"address\":\"70 Summer walk, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
				.when().log().all().put("maps/api/place/update/json").then().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
		
		
		String response1 =given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		.when().log().all().get("maps/api/place/get/json").then().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReUseableMethods.rowtoJson(response1);
		
		String address =js1.getString("address");
		
		System.out.println("address is-----"+address);
		
		Assert.assertEquals(address, "70 Summer walk, USA");
		

	}


}
