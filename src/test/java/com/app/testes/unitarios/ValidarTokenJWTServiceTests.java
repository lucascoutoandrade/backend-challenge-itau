package com.app.testes.unitarios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.dados.Dados;
import com.app.service.ValidarTokenJWTService;

public class ValidarTokenJWTServiceTests {

	@ExtendWith(MockitoExtension.class)
	@InjectMocks
	private ValidarTokenJWTService validarTokenJWTService;

	Dados dados = new Dados();

	@Test
	void deveValidarJwtValido() {

		Assertions.assertTrue(validarTokenJWTService.validateJwt(dados.getJwtValido()));
	}

	@Test
	void deveValidarJwtInvalido() {

		Assertions.assertTrue(!validarTokenJWTService.validateJwt(dados.getJwtInvalido()));
	}
	
	@Test
	void deveValidarJwtComClaimNameInvalida() {

		Assertions.assertTrue(!validarTokenJWTService.validateJwt(dados.getJwtInvalidoClaimName()));
	}
	
	@Test
	void deveValidarJwtComMaisTresClaims() {

		Assertions.assertTrue(!validarTokenJWTService.validateJwt(dados.getJwtComMaisTresClaims()));
	}
	
	@Test
	void deveValidarNumeroPrimo() {
		int value = 29;
		boolean isPrimo = ValidarTokenJWTService.isPrimo(value);
		Assertions.assertTrue(isPrimo,"Valor: "+value+" não é primo");
		
	}
	
	@Test
	void deveValidarComRetornoNegativoNumeroNaoPrimo() {
		int value = 28;
		boolean isPrimo = ValidarTokenJWTService.isPrimo(value);
		Assertions.assertTrue(!isPrimo,"Valor: "+value+"  é primo");
		
	}
	
	@Test
	void deveValidarClaimName() {
		String nome = "Teste";
		boolean isClaimNomeOk = validarTokenJWTService.verificaClaimName(nome);
		Assertions.assertTrue(isClaimNomeOk,"Valor: "+nome+" não esta de acordo com as regras definidas");
		
	}
	
	@Test
	void deveValidarComRetornoNegativoClaimNameComNumero() {
		String nome = "T8st9";
		boolean isClaimNomeOk = validarTokenJWTService.verificaClaimName(nome);
		Assertions.assertTrue(!isClaimNomeOk,"Valor: "+nome+" esta de acordo com as regras definidas");
		
	}
	
	@Test
	void deveValidarComRetornoNegativoClaimNameComMaisDe256Caracteres() {
		String nome = "asalçaskdlçakdlçkasldkçlsakdlçskldkjsdjskldhjshdjksdjkhskdsjhdjsjshdjksdjksdjshdjksdjksdjshdjksdjshdshdkjsjksdjkhsjdkhsjkjsdkhsdsdjkhsdkjkjsdjksosidosidosidosidosidosidosidosidosidosiodisoidosidosidosidosidosidosidosidsoidosidosidosidosidosidosiodosidosidsoi";
		boolean isClaimNomeOk = validarTokenJWTService.verificaClaimName(nome);
		Assertions.assertTrue(!isClaimNomeOk,"Valor: "+nome+" esta de acordo com as regras definidas");
		
	}
	
	@Test
	void deveValidarClaimRole() {
		String role = "Admin";
		boolean isClaimRole = validarTokenJWTService.verificaClaimRole(role);
		Assertions.assertTrue(isClaimRole,"Valor: "+role+" não esta de acordo com as regras definidas");
		
	}
	
	@Test
	void deveValidarComRetornoNegativoRoleQueNaoEstaNaLista() {
		String role = "DEV";
		boolean isClaimRole = validarTokenJWTService.verificaClaimRole(role);
		Assertions.assertTrue(!isClaimRole,"Valor: "+role+" esta de acordo com as regras definidas");
		
	}

}
