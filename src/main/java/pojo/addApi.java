package pojo;


import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;


public class addApi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//serialization--converting java obj to payload/body--sending in request
		//create pojo classes by referring jsonbody.json
		//here using obj.setter method to set value to attributes and making body
		add a=new add();
		a.setAccuracy(50);
		a.setName("Frontline house");
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		
		List<String> typ=new ArrayList<String>();
		typ.add("shoe park");
		typ.add("shoe");
		a.setTypes(typ);
		
		location l=new location();
		l.setLat(-38.383494);
		l.setLng(-33.383494);
		a.setLocation(l);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String res=given().log().all().queryParam("key", "qaclick123")
				.body(a)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(res);
		System.out.println(js);
	}

}
