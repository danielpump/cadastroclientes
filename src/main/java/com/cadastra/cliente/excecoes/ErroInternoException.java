/**
 * 
 */
package com.cadastra.cliente.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de negócio especifica da aplicação
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroInternoException  extends RuntimeException{

	public ErroInternoException(String message) {
		super(message);
	}
	
}
