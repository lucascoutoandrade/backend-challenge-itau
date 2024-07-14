package com.app.model;

public class TokenJWT {
    
    String value, name, role;
    long seed;
    boolean isTokenJWTValid;
    
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

	boolean test;

    public TokenJWT( String value) {
        this.value = value;
    }
    public TokenJWT() {
      
    }
  

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void setValue(boolean value) {
        this.test = value;
    }
}
