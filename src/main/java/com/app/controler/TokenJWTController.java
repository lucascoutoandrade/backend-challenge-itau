package com.app.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody ;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.TokenJWT;
import com.app.service.ValidarTokenJWTService;


@RestController
public class TokenJWTController {
	
//	private static final Pattern NOME_PATTERN = Pattern.compile("\\D+");
//	private static final Pattern REGRA_PATTERN = Pattern.compile("^(Admin|Member|External)$");
//	private ValidarTokenJWTService validarJWT;
	
//	@Autowired
//	public TokenJWTController(ValidarTokenJWTService validarJWT) {
//		
//		this.validarJWT = validarJWT;
//		
//	}
	
	@Autowired
	private ValidarTokenJWTService validarJWTService;
	
	@PostMapping("/validate")
	
	public ResponseEntity<String> validate(@RequestBody String jwt) {

		// altere esse metodo para atender as regras de definidas no readme.
		// você pode modificar o tipo de retorno, importar outros pacotes, criar mais classes.
		// existe uma pasta chamada Model para gerenciar o objeto Token

		// Imprimindo o input recebido
		//System.out.println(token.getValue());
		TokenJWT tokenjwt = validarJWTService.validateJwt(jwt);
		
		if(tokenjwt != null) {
			
			return ResponseEntity.ok("JWT Valido");
		}else {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT inválido");
		}
		
	}
	
	@GetMapping("/validateJwt")
	 public ResponseEntity<String> validateJwt(@RequestParam String jwt) {
		TokenJWT jwtToken = validarJWTService.validateJwt(jwt);

	        if (jwtToken != null) {
	            return ResponseEntity.ok("JWT válido");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT inválido");
	        }
	    }
	
	
	@GetMapping("/test")
	 public String test() {
		 return "test";
	    }
	
	
	
	
}


