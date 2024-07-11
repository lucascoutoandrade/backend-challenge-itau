package com.tokenvalidator.app.controler;
import org.springframework.web.bind.annotation.RequestBody ;

import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokenvalidator.app.model.Token;

@RestController
public class TokenController {
	
	private static final Pattern NOME_PATTERN = Pattern.compile("\\D+");
	private static final Pattern REGRA_PATTERN = Pattern.compile("^(Admin|Member|External)$");

	@PostMapping(value="/validate")
	public String validate( @RequestBody Token token) {

		// altere esse metodo para atender as regras de definidas no readme.
		// você pode modificar o tipo de retorno, importar outros pacotes, criar mais classes.
		// existe uma pasta chamada Model para gerenciar o objeto Token

		// Imprimindo o input recebido
		//System.out.println(token.getValue());
		
		

		return "false";
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


