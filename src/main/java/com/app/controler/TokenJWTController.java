package com.app.controler;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.model.TokenJWT;
import com.app.service.ValidarTokenJWTService;

@RestController
public class TokenJWTController {

	private TokenJWT tokenJwtModel = new TokenJWT();

	@Autowired
	private ValidarTokenJWTService validarJWTService;

	@PostMapping(value = "/validate")

	public ResponseEntity<Map<String, String>> validate(@RequestBody String jwt) {

		boolean isValidTokenjwt = validarJWTService.validateJwt(jwt);

		if (isValidTokenjwt == true) {
			return ResponseEntity.ok().body(Collections.singletonMap("message", tokenJwtModel.getResponseMsg()));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("message", tokenJwtModel.getResponseMsg()));
		}
	}

	@GetMapping("/validateJwt")
	public ResponseEntity<Map<String, String>> validateJwt(@RequestParam("jwt") String jwt) {

		boolean jwtToken = validarJWTService.validateJwt(jwt);

		if (jwtToken == true) {
			return ResponseEntity.ok().body(Collections.singletonMap("message", tokenJwtModel.getResponseMsg()));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Collections.singletonMap("message", tokenJwtModel.getResponseMsg()));
		}
	}

	@GetMapping("/test")
	public String test() {
		return "test";
	}

}
