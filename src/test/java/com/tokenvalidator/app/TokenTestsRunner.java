package com.tokenvalidator.app;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TokenTestsRunner {
	
	private JSONObject jsonObject = new JSONObject();
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
	
	@BeforeAll
	public static void configInicial() {
		
		RestAssured.baseURI="http://localhost";
//		RestAssured.basePath = "/validate";
		RestAssured.port=8000;
		
	}

	@Test
	public void deveVerificarAplicacaoEstaNoAr() throws JSONException {
		
		jsonObject.put("value", token);

		Response responseBody = RestAssured.given()
				.when().
				body(jsonObject.toString())
				.post("/validate")
				.then()
				.extract()
				.response();
		
		Assertions.assertEquals(200, responseBody.getStatusCode());
		Assertions.assertEquals("JWT Válido", responseBody.getBody().asPrettyString());

				
	
	
	}


}
