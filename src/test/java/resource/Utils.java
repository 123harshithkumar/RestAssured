package resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	//when we run many tests/tests with multiple data sets[scenario outline],below method will be invoked multiple times hence 
	//newly log eill overrite previous one and specbuilder has to run only once at the beginnig so check if req==null,then execute
	//here req is static bcz, it holds value till execution ends from the begining,if not static--it will be null whenever we call this method 2nd 3rd time..
	static RequestSpecification req;
	public RequestSpecification requestSpec() throws Exception {

		if(req==null) {
			PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
			req=new RequestSpecBuilder().setBaseUri(getGlobalProp("baseUri")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;

	}

	public String getGlobalProp(String key) throws IOException {
		Properties prop=new Properties();
		FileInputStream in=new FileInputStream("src//test//java//resource/global.properties");
		prop.load(in);
		return prop.getProperty(key);
	}
	public String getValue(Response response,String key) {

		String resString=response.asString();
		JsonPath js=new JsonPath(resString);
		return js.get(key).toString();
	}
}
