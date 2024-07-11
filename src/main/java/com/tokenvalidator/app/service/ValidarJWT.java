package com.tokenvalidator.app.service;

public class ValidarJWT {
	
	
	
private static boolean isClaimValido() {
		
		return false;
}
	
	
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

}
