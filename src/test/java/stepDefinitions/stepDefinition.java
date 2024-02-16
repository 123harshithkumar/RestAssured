package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resource.APIResources;
import resource.TestData;
import resource.Utils;

public class stepDefinition extends Utils {

	ResponseSpecification resp;
	RequestSpecification req1;
	Response res;
	static String placeID; //static because we need to use value of 1st test in second test, if not initialized with static, value will be null at the begining of 2nd test
	TestData ob=new TestData();
	//Utils util=new Utils();
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String lang, String address) throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		//-use of spec builder--by parent util class under resource package,it has spec builder
		req1=given().spec(requestSpec()).body(ob.addPlacePaylod(name,lang,address));
	}
	@When("user calls {string} API with http {string} method")
	public void user_calls_api_with_http_post_method(String resource,String method) {
	    // Write code here that turns the phrase above into concrete actions
		resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		//resource should work for all resources, so pass API name from feature file and get resource for specific API from enum class
		//enum class obj creation is little different--observe below lines
		
		APIResources enumReso=APIResources.valueOf(resource); //constructor will be called with resource we pass
		System.out.println(enumReso.getResource());
		if(method.equalsIgnoreCase("Post")) {
			res=req1.when().post(enumReso.getResource());
		}else if(method.equalsIgnoreCase("Get")) {
			res=req1.when().get(enumReso.getResource());
		}else if(method.equalsIgnoreCase("Delete")) {
			res=req1.when().delete(enumReso.getResource());
		}
		

	}
	@Then("Api call is successull with status code {int}")
	public void api_call_is_successull_with_status_code(Integer int1) {
		res=res.then().log().all().spec(resp).extract().response();
	   assertEquals(res.getStatusCode(), 200); //using jumit assert statement
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	   //getValue is generic method get get specific value from response--refer utils class
	    assertEquals(getValue(res,key),value); //get value for specific key and compare with expected value--generic line,compare with feature file
	}
	
	//here, using getPlaceapi validate response,reuse methods/generic methods as possible here
	@Then("verify place_id create maps to {string} using {string}")
	public void verify_place_id_create_maps_to_using(String expectedname, String resource) throws Exception {
	    placeID=getValue(res,"place_id");
	    req1=given().spec(requestSpec()).queryParam("place_id", placeID);
	    
	    user_calls_api_with_http_post_method(resource,"Get");//reusing above method
	    //now response var res--holds getapi response
	    String atctiualname=getValue(res,"name");
	    assertEquals(atctiualname,expectedname);
	      
	    
	}
	
	@Given("delete place api payload")
	public void delete_place_api_payload() throws Exception {
		req1=given().spec(requestSpec()).body(ob.deletePlacePayload(placeID)); //getting body from testData class by passing placeID bcz, its dynamic
	}
}
