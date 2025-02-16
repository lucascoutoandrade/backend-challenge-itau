package com.app.service;

import java.util.Base64;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.app.model.TokenJWT;

@Service
public class ValidarTokenJWTService {

	private TokenJWT tokenJWT = new TokenJWT();

	public boolean validateJwt(String jwt) {

		String[] arrTokenJwt = jwt.split(":");
		String tokenJwtRecebido = null;

		tokenJwtRecebido = (arrTokenJwt.length == 2) ? arrTokenJwt[1].replace("}", "").replace("\"", "").trim() : jwt;
		String[] strClaims = decodePayload(tokenJwtRecebido);
		try {

			// Deve conter claim valida
			if (strClaims == null) {
				TokenJWT.setResponseMsg("JWT inválido");
				return false;

			}

			// Deve conter apenas 3 claims
			if (strClaims.length != 3) {

				TokenJWT.setResponseMsg("JWT não contem 3 claims");
				return false;

			}

			// Verifica claim 'Name
			if (!verificaClaimName(tokenJWT.getName())) {

				return false;
			}

			// Verifica claim 'Role'
			if (!verificaClaimRole(tokenJWT.getRole())) {
				return false;
			}

			// Verifica claim 'Seed'
			if(!verificaClaimSeed((int)tokenJWT.getSeed())) {
				return false;
				
			}

			// Se todos os checks passar
			tokenJWT.setTokenJWTValid(true);
			TokenJWT.setResponseMsg("JWT Válido");

			return true;

		} catch (Exception e) {

			System.out.println("Validacao JWT Falhou: " + e.getMessage());
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

			System.out.println(
					"{{JWT Invalida}} Não foi possivel converter string JSON em um objeto JSON: " + e.getMessage());

		}

		return claimsArray;

	}

	public boolean verificaClaimRole(String claimRole) {

		String role = claimRole;
		Set<String> validRoles = Set.of("Admin", "Member", "External");
		if (role == null || !validRoles.contains(role)) {

			TokenJWT.setResponseMsg("Inválido claim Role");
			return false;
		}

		return true;

	}

	public boolean verificaClaimSeed(int claimSeed) {

		Integer seed = claimSeed;
		if (seed == null || !isPrimo(seed)) {

			TokenJWT.setResponseMsg("Inválido claim Seed (Não é um numero primo)");
			return false;
		}

		return true;
	}

	public boolean verificaClaimName(String claimName) {

		// Verifica claim 'Name'
		String name = claimName;
		if (name == null || name.length() > 256 || name.matches(".*\\d.*")) {

			TokenJWT.setResponseMsg("Inválido claim Name");

			return false;
		}

		return true;
	}

	// Valida se numero e primo
	public static boolean isPrimo(long n) {
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

}
