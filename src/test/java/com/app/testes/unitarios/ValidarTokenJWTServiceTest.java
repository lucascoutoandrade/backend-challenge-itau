package com.app.testes.unitarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.dados.Dados;
import com.app.service.ValidarTokenJWTService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValidarTokenJWTServiceTest {

	@ExtendWith(MockitoExtension.class)
	@InjectMocks
	private ValidarTokenJWTService validarTokenJWTService;

	private Dados dados = new Dados();

	@Test
	@Order(1)
	void deveValidarJwtValido() {

		Assertions.assertTrue(validarTokenJWTService.validateJwt(dados.getJwtValido()));
	}

	@Test
	@Order(2)
	void deveValidarJwtInvalido() {

		Assertions.assertTrue(!validarTokenJWTService.validateJwt(dados.getJwtInvalido()));
	}
	
	@Test
	@Order(3)
	void deveValidarJwtComClaimNameInvalida() {

		Assertions.assertTrue(!validarTokenJWTService.validateJwt(dados.getJwtInvalidoClaimName()));
	}
	
	@Test
	@Order(4)
	void deveValidarJwtComMaisTresClaims() {

		Assertions.assertTrue(!validarTokenJWTService.validateJwt(dados.getJwtComMaisTresClaims()));
	}
	
	@Test
	@Order(5)
	void deveValidarNumeroPrimo() {
		int value = 29;
		boolean isPrimo = ValidarTokenJWTService.isPrimo(value);
		Assertions.assertTrue(isPrimo,"Valor: "+value+" não é primo");
		
	}
	
	@Test
	@Order(6)
	void deveValidarComRetornoNegativoNumeroNaoPrimo() {
		int value = 28;
		boolean isPrimo = ValidarTokenJWTService.isPrimo(value);
		Assertions.assertTrue(!isPrimo,"Valor: "+value+"  é primo");
		
	}
	
	@Test
	@Order(7)
	void deveValidarClaimName() {
		String nome = "Teste";
		boolean isClaimNomeOk = validarTokenJWTService.verificaClaimName(nome);
		Assertions.assertTrue(isClaimNomeOk,"Valor: "+nome+" não esta de acordo com as regras definidas");
		
	}
	
	@Test
	@Order(8)
	void deveValidarComRetornoNegativoClaimNameComNumero() {
		String nome = "T8st9";
		boolean isClaimNomeOk = validarTokenJWTService.verificaClaimName(nome);
		Assertions.assertTrue(!isClaimNomeOk,"Valor: "+nome+" esta de acordo com as regras definidas");
		
	}
	
	@Test
	@Order(9)
	void deveValidarComRetornoNegativoClaimNameComMaisDe256Caracteres() {
		String nome = "asalçaskdlçakdlçkasldkçlsakdlçskldkjsdjskldhjshdjksdjkhskdsjhdjsjshdjksdjksdjshdjksdjksdjshdjksdjshdshdkjsjksdjkhsjdkhsjkjsdkhsdsdjkhsdkjkjsdjksosidosidosidosidosidosidosidosidosidosiodisoidosidosidosidosidosidosidosidsoidosidosidosidosidosidosiodosidosidsoi";
		boolean isClaimNomeOk = validarTokenJWTService.verificaClaimName(nome);
		Assertions.assertTrue(!isClaimNomeOk,"Valor: "+nome+" esta de acordo com as regras definidas");
		
	}
	
	@Test
	@Order(10)
	void deveValidarClaimRole() {
		String role = "Admin";
		boolean isClaimRole = validarTokenJWTService.verificaClaimRole(role);
		Assertions.assertTrue(isClaimRole,"Valor: "+role+" não esta de acordo com as regras definidas");
		
	}
	
	@Test
	@Order(11)
	void deveValidarComRetornoNegativoRoleQueNaoEstaNaLista() {
		String role = "DEV";
		boolean isClaimRole = validarTokenJWTService.verificaClaimRole(role);
		Assertions.assertTrue(!isClaimRole,"Valor: "+role+" esta de acordo com as regras definidas");
		
	}
	
	@Test
	@Order(12)
	void deveValidarClaimSeed() {
		int seed = 7841;
		boolean isClaimSeed = validarTokenJWTService.verificaClaimSeed(seed);
		Assertions.assertTrue(isClaimSeed,"Valor: "+seed+" esta de acordo com as regras definidas");
			
	}
	
	@Test
	@Order(13)
	void deveValidarComRetornoNegativoSeedComValorDiferentePrimo() {
		int seed = 240;
		boolean isClaimSeed = validarTokenJWTService.verificaClaimSeed(seed);
		Assertions.assertTrue(!isClaimSeed,"Valor: "+seed+" esta de acordo com as regras definidas");
			
	}

}
