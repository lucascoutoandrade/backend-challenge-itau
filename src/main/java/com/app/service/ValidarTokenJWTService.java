package com.app.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.app.model.TokenJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.security.Keys;

@Service
public class ValidarTokenJWTService {

private static final String SECRET_KEY = "amF2YV90ZXN0X2x1Y2FzX3NwcmluZ19ib290X2JvcmFfbGE=";	

//SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
SecretKey secret = Keys.hmacShaKeyFor("7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
		.getBytes(StandardCharsets.UTF_8));

	public boolean validateJwt(String jwt) {
		TokenJWT tokenJWT = new TokenJWT();
//		String [] tokenJwt = jwt.split(":");

		try {
			Jws<Claims> claimsJws = 
					Jwts.parserBuilder()
					.setSigningKey(secret)
					.build()
//					.parseClaimsJws(tokenJwt[1].replace("}","").replace("\"","").trim());
					.parseClaimsJws(jwt);

			Claims claims = claimsJws.getBody();

			// Deve conter apenas 3 claims
			if (claims.size() != 3) {
//            response.setValue(false);
//            response.setValue("JWT must contain exactly 3 claims");
//				tokenJWT.setTokenJWTValid(false);
				return false;
				
			}

			// Verifica claim 'Name'
			String name = claims.get("Name", String.class);
			if (name == null || name.length() > 256 || name.matches(".*\\d.*")) {
//            response.setValue(false);
//            response.setValue("Invalid Name claim");
//				tokenJWT.setTokenJWTValid(false);
				return false;
			}

			// Verifica claim 'Role'
			String role = claims.get("Role", String.class);
			Set<String> validRoles = Set.of("Admin", "Member", "External");
			if (role == null || !validRoles.contains(role)) {
//            response.setValue(false);
//            response.setValue("Invalid Role claim");
//				tokenJWT.setTokenJWTValid(false);
				return false;
			}

			// Verifica claim 'Role'
			Integer seed = claims.get("Seed", Integer.class);
			if (seed == null || !isPrimo(seed)) {
//            response.setValue(false);
//            response.setValue("Invalid Seed claim (not a prime number)");
//				tokenJWT.setTokenJWTValid(false);
				return false;
			}

			// Se todos os checks passar
	
			tokenJWT.setName(name);
			tokenJWT.setRole(role);
			tokenJWT.setSeed(seed);
			tokenJWT.setTokenJWTValid(true);
			
			return true;

//        tokenJWT.setValue(true);
//        tokenJWT.setValue("JWT is valid");

		} catch (Exception e) {
//    	tokenJWT.setValue(false);
//    	tokenJWT.setValue("JWT validation failed: " + e.getMessage());
//			return null;
			tokenJWT.setTokenJWTValid(false);
			return false;
		}

		
	}

	// Valida se numero e primo
	private static boolean isPrimo(long n) {
		if (n <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	// Gera chave secreta
	private static Key getSecretKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

}
