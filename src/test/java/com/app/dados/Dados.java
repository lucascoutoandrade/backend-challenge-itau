package com.app.dados;

import java.util.Properties;

import utils.Config;

public class Dados {

	private static Properties prop = Config.getProp();

	public String getJwtValido() {

		return prop.getProperty("jwt.token.valido");

	}

	public String getJwtInvalido() {

		return prop.getProperty("jwt.token.invalido");

	}

	public String getJwtInvalidoClaimName() {

		return prop.getProperty("jwt.token.invalidoClaimName");
	}

	public String getJwtComMaisTresClaims() {

		return prop.getProperty("jwt.token.jwtMaisTresClaims");
	}

}
