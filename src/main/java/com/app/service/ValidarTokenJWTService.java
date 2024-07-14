package com.app.service;

import java.security.Key;
import java.util.Set;
import org.springframework.stereotype.Service;

import com.app.model.TokenJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class ValidarTokenJWTService {

private static final String SECRET_KEY = "test_lucas";	

	public TokenJWT validateJwt(String jwt) {

	

		try {
			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(jwt);

			Claims claims = claimsJws.getBody();

			// Deve conter apenas 3 claims
			if (claims.size() != 3) {
//            response.setValue(false);
//            response.setValue("JWT must contain exactly 3 claims");
				return null;
			}

			// Verifica claim 'Name'
			String name = claims.get("Name", String.class);
			if (name == null || name.length() > 256 || name.matches(".*\\d.*")) {
//            response.setValue(false);
//            response.setValue("Invalid Name claim");
				return null;
			}

			// Verifica claim 'Role'
			String role = claims.get("Role", String.class);
			Set<String> validRoles = Set.of("Admin", "Member", "External");
			if (role == null || !validRoles.contains(role)) {
//            response.setValue(false);
//            response.setValue("Invalid Role claim");
				return null;
			}

			// Verifica claim 'Role'
			Integer seed = claims.get("Seed", Integer.class);
			if (seed == null || !isPrimo(seed)) {
//            response.setValue(false);
//            response.setValue("Invalid Seed claim (not a prime number)");
				return null;
			}

			// Se todos os checks passar
			TokenJWT tokenJWT = new TokenJWT();
			tokenJWT.setName(name);
			tokenJWT.setRole(role);
			tokenJWT.setSeed(seed);
			
			return tokenJWT;

//        tokenJWT.setValue(true);
//        tokenJWT.setValue("JWT is valid");

		} catch (Exception e) {
//    	tokenJWT.setValue(false);
//    	tokenJWT.setValue("JWT validation failed: " + e.getMessage());
			return null;
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
