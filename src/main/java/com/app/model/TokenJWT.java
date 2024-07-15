package com.app.model;

public class TokenJWT {

	String name, role;
	static String responseMsg;
	long seed;
	boolean isTokenJWTValid;

	public TokenJWT() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTokenJWTValid() {
		return isTokenJWTValid;
	}

	public void setTokenJWTValid(boolean isTokenJWTValid) {
		this.isTokenJWTValid = isTokenJWTValid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}
	
	public String getResponseMsg() {
		return responseMsg;
	}

	public static void setResponseMsg(String responseMsg) {
		TokenJWT.responseMsg = responseMsg;
	}

}
