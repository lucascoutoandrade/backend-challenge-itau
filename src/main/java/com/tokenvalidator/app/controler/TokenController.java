package com.tokenvalidator.app.controler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody ;
import org.springframework.web.bind.annotation.RestController;
import com.tokenvalidator.app.model.Token;
import com.tokenvalidator.app.service.ValidarJWT;

@RestController
public class TokenController {
	
//	private static final Pattern NOME_PATTERN = Pattern.compile("\\D+");
//	private static final Pattern REGRA_PATTERN = Pattern.compile("^(Admin|Member|External)$");
	ValidarJWT validarJWT = new ValidarJWT();
	@PostMapping(value="/validate")
	public String validate( @RequestBody Token token) {

		// altere esse metodo para atender as regras de definidas no readme.
		// você pode modificar o tipo de retorno, importar outros pacotes, criar mais classes.
		// existe uma pasta chamada Model para gerenciar o objeto Token

		// Imprimindo o input recebido
		//System.out.println(token.getValue());
		
		
		

		return "teste";
	}
	
	
	
	
}


