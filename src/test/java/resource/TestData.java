package resource;

import java.util.ArrayList;
import java.util.List;

import pojo.add;
import pojo.location;

public class TestData {

	//We can take values for paylod/body frpm feature file instead of hard coding--DYNAIC Json/Body
	public add addPlacePaylod(String name,String lang,String address) {
		add a=new add();
		a.setAccuracy(50);
		a.setName(name);
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress(address);
		a.setWebsite("http://google.com");
		a.setLanguage(lang);
		
		List<String> typ=new ArrayList<String>();
		typ.add("shoe park");
		typ.add("shoe");
		a.setTypes(typ);
		
		location l=new location();
		l.setLat(-38.383494);
		l.setLng(-33.383494);
		a.setLocation(l);
		return a;
	}
	public String deletePlacePayload(String placeID) {
		return "{\r\n"
				+ "    \"place_id\": \""+placeID+"\"\r\n"
				+ "}";
	}
}
