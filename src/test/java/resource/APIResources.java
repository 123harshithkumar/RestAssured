package resource;
//enum is special class which has collection of constants/methods
//replace class with enum keyword
//have methodname("resource")
//create private variable,constructor where initialize value to private var
//get method to get specific resource value from tests---where parameter for method is AddPlaceAPI,get delete---case sensitive
public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	
	APIResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
