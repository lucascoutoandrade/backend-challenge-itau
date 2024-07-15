package request;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class RequestTest {
	
	Request requestTest = new Request();
	String pathOK = "http://localhost:8000/validateJwt";
	String pathOKInvalidMethod = "http://localhost:8000/validate";
	String paramJwt = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
	@Test	
	public void deveVerificarAplicacaoNaoAceitaInvalidoMetodo() {
		
		requestTest.responseTest(pathOKInvalidMethod)
		.then()
		.log().all()
		.statusCode(405)
		.body("error", Matchers.containsString("Method Not Allowed"));
		
		
	}
	
	@Test	
	public void deveVerificarAplicacaoNaoAceitaChamadaSemPassagemParametroJWT() {
		
		requestTest.responseTest(pathOKInvalidMethod)
		.then()
		.log().all()
		.statusCode(400)
		.body("error", Matchers.containsString("Bad Request"));
		
		
	}
	
	@Test
	public void deveVerificarAplicacaoEstaNoAr() {
		
		requestTest.responseTest(pathOKInvalidMethod)
		.then()
		.log().all()
		.statusCode(200)
		.body("", Matchers.containsString("JWT Válido"));
		
		
		
	}

}
