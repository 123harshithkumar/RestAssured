package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

	//this method is added to get place_id
	//this has to run BEFORE only when we run @deletePlace alone else shouldnot run,so add if cond 
	@Before("@DeletePlace")
	public void beforeScenario() throws Exception {
		stepDefinition sd=new stepDefinition();
		if(stepDefinition.placeID==null) { //accessing static variables--classname.var
			sd.add_place_payload_with("harshith", "kannada", "3rd cross");//add place
            sd.user_calls_api_with_http_post_method("AddPlaceAPI", "Post");//hit api
            sd.verify_place_id_create_maps_to_using("harshith", "GetPlaceAPI"); //extract place_id
		}
	}
}
