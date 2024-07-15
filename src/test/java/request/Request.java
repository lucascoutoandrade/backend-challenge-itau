package request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Request {
	
	    public Response responseTest(String path) {
	        return RestAssured.given()
	                .contentType(ContentType.JSON)
	                .when()
	                .get(path);
	    }

}
