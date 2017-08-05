package com.cadastra.cliente.controles;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cadastra.cliente.excecoes.NegocioException;

@ControllerAdvice
public class ErrosControle {

	@ExceptionHandler(NegocioException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Map<String, Object> conflict(HttpServletRequest req, Exception e) {
//		log.error(e.getMessage(), e);
		System.out.println(e.getMessage());
		return null;
	}

}
