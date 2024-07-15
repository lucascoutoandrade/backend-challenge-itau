package com.app.service;

import java.util.Base64;
import java.util.Set;
import javax.crypto.SecretKey;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.app.model.TokenJWT;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class ValidarTokenJWTService {

	TokenJWT tokenJWT = new TokenJWT();

	private static final String SECRET_KEY = "ZGQlODgqMzQ3ZjZkJmbCoyQkwqMkRmRyZEZoMzNmc3NER14hMw==";

	SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));

//SecretKey secret = Keys.hmacShaKeyFor("7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
//		.getBytes(StandardCharsets.UTF_8));

	public boolean validateJwt(String jwt) {

		String[] arrTokenJwt = jwt.split(":");
//		String test = jwt.substring(7);
//		String test = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
		String[] strClaims = decodePayload(arrTokenJwt[1].replace("}", "").replace("\"", "").trim());
		try {

			if(strClaims == null) {
				TokenJWT.setResponseMsg("JWT inválido");
				return false;
				
			}

			// Deve conter apenas 3 claims
			if (strClaims.length != 3) {
//            response.setValue(false);
//            response.setValue("JWT must contain exactly 3 claims");
//				tokenJWT.setTokenJWTValid(false);
				TokenJWT.setResponseMsg("JWT não contem 3 claims");
				return false;

			}

			// Verifica claim 'Name'
			String name = tokenJWT.getName();
			if (name == null || name.length() > 256 || name.matches(".*\\d.*")) {
//            response.setValue(false);
//            response.setValue("Invalid Name claim");
//				tokenJWT.setTokenJWTValid(false);
				TokenJWT.setResponseMsg("Inválido claim Name");
				return false;
			}

			// Verifica claim 'Role'
			String role = tokenJWT.getRole();
			Set<String> validRoles = Set.of("Admin", "Member", "External");
			if (role == null || !validRoles.contains(role)) {
//            response.setValue(false);
//            response.setValue("Invalid Role claim");
//				tokenJWT.setTokenJWTValid(false);
				TokenJWT.setResponseMsg("Inválido claim Role");
				return false;
			}

			// Verifica claim 'Role'
			Integer seed = (int) tokenJWT.getSeed();
			if (seed == null || !isPrimo(seed)) {
//            response.setValue(false);
//            response.setValue("Invalid Seed claim (not a prime number)");
//				tokenJWT.setTokenJWTValid(false);
				TokenJWT.setResponseMsg("Inválido claim Role (Não é um numero primo)");
				return false;
			}

			// Se todos os checks passar
			tokenJWT.setName(name);
			tokenJWT.setRole(role);
			tokenJWT.setSeed(seed);
			tokenJWT.setTokenJWTValid(true);
			TokenJWT.setResponseMsg("JWT Válido");

			return true;

//        tokenJWT.setValue(true);
//        tokenJWT.setValue("JWT is valid");

		} catch (Exception e) {

			System.out.println("JWT validation failed: " + e.getMessage());
			tokenJWT.setTokenJWTValid(false);
			return false;
		}

	}

	private String[] decodePayload(String jwt) {

		String[] jwtParts = jwt.split("\\.");
		String[] claimsArray = null;

		if (jwtParts.length < 2) {
			System.out.println("O JWT não possui partes suficientes.");

		}

		String base64EncodedClaims = jwtParts[1];

		byte[] decodedBytes = Base64.getUrlDecoder().decode(base64EncodedClaims);

		// Converter os bytes decodificados em uma string JSON
		String decodedClaims = new String(decodedBytes);
		
		try {
			// Converter a string JSON em um objeto JSON
			JSONObject claimsObject = new JSONObject(decodedClaims);

			tokenJWT.setRole(claimsObject.getString("Role"));
			tokenJWT.setName(claimsObject.getString("Name"));
			tokenJWT.setSeed(claimsObject.getLong("Seed"));

			claimsArray = new String[claimsObject.length()];
		} catch (JSONException e) {

			System.out.println("Não foi possivel converter a o token informado: " + e.getMessage());

		}

		return claimsArray;

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
//	private static Key getSecretKey() {
//		
//	
//		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//	}

}
