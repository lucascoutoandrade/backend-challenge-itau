package com.app.testes.integracao;

import java.util.Properties;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.app.dados.Dados;

import io.restassured.RestAssured;
import utils.Config;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class IntegracaoTest {
	
	private static Properties prop = Config.getProp();
	private Dados dados = new Dados();
	private JSONObject jsonObject = new JSONObject();
	
	@BeforeAll
	public static void configInicial() {
		
		RestAssured.baseURI=prop.getProperty("api.baseUri");
		
	}

	@Test
	@Order(1)
	public void deveVerificarJwtValido() throws JSONException {
		
		jsonObject.put("value", dados.getJwtValido());
		
		RestAssured.given()
				.when().
				body(jsonObject.toString())
				.post("/validate")
				.then()
				.statusCode(200)
				.body("message", Matchers.containsString("JWT Válido"),
					  "message", Matchers.instanceOf(String.class),
					  "message", Matchers.not(Matchers.blankOrNullString())
						)
				
				;

	}
	
	@Test
	@Order(2)
	public void deveVerificarJwtInvalido() throws JSONException {
		
		jsonObject.put("value", dados.getJwtInvalido());
		
		RestAssured.given()
				.when().
				body(jsonObject.toString())
				.post("/validate")
				.then()
				.statusCode(401)
				.body("message", Matchers.containsString("JWT inválido"),
					  "message", Matchers.instanceOf(String.class),
					  "message", Matchers.not(Matchers.blankOrNullString())
						);
		
		
	}
	
	@Test
	@Order(3)
	public void deveVerificarJwtInvalidoClaimName() throws JSONException {
		
		
		jsonObject.put("value", dados.getJwtInvalidoClaimName());
		
		RestAssured.given()
				.when().
				body(jsonObject.toString())
				.post("/validate")
				.then()
				.statusCode(401)
				.body("message", Matchers.containsString("Inválido claim Name"),
					  "message", Matchers.instanceOf(String.class),
					  "message", Matchers.not(Matchers.blankOrNullString())
						);
		
		
	}
	
	@Test
	@Order(4)
	public void deveVerificarJwtComMaisTrezClaims() throws JSONException {
		
		
		jsonObject.put("value", dados.getJwtComMaisTresClaims());
		
		RestAssured.given()
				.when().
				body(jsonObject.toString())
				.post("/validate")
				.then()
				.statusCode(401)
				.body("message", Matchers.containsString("JWT não contem 3 claims"),
					  "message", Matchers.instanceOf(String.class),
					  "message", Matchers.not(Matchers.blankOrNullString())
						);
		
		
	}
	@Test
	@Order(5)
	public void deveVerificarRecursoInexistente() throws JSONException {
		
		jsonObject.put("value", dados.getJwtValido());
		RestAssured.given()
		.when().
		body(jsonObject.toString())
		.post("/validates")
		.then()
		.statusCode(404)
		.body("error", Matchers.containsString("Not Found"),
			  "error", Matchers.instanceOf(String.class),
			  "error", Matchers.not(Matchers.blankOrNullString())
				);
		
		
	}
	@Test
	@Order(6)
	public void deveVerificarMetodoNaoAutorizado() throws JSONException {
		
		jsonObject.put("value", dados.getJwtValido());
		RestAssured.given()
		.when().
		body(jsonObject.toString())
		.get("/validate")
		.then()
		.statusCode(405)
		.body("error", Matchers.containsString("Method Not Allowed"),
			  "error", Matchers.instanceOf(String.class),
			  "error", Matchers.not(Matchers.blankOrNullString())
				);
		
		
		
		
	}
	
	
	


}
