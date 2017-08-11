package com.cadastra.cliente.servicos.validadores.carro;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.servicos.validadores.Validador;

/**
 * Verifica se o campo modelo est� em formato v�lido
 * 
 * @author Daniel Ferraz
 * @since 11 de ago de 2017
 *
 */
@Component
public class ModeloValidador implements Validador<Carro> {

	@Override
	public void estaValido(Carro carro) {
		if (StringUtils.isEmpty(carro.getModelo())) {
			throw new NegocioException("Carro sem campo modelo");
		}
	}

}
