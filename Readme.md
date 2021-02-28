### RestAssured QuickView

```java
RestAssured.baseURI = baseUrl;
	 given()..queryParam("key","value")
	.header("Content-Type","Application/json")
	.body("xxx").when().post({resourcePath})
	.then().assertThat().statusCode(200)


```
#### JsonPath to read json
```java
	JsonPath js = new JsonPath(response);
	String place_id =js.getString("place_id");

```

#### Junit for Assertions
```java
	Assert.assertEquals(place_id, "70 Summer walk, USA");
``` 