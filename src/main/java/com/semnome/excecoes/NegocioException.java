/**
 * 
 */
package com.semnome.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
public class NegocioException  extends RuntimeException{

	public NegocioException(String message) {
		super(message);
	}
	
}
