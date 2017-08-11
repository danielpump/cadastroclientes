package com.cadastra.cliente.servicos.validadores.carro;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.servicos.validadores.Validador;

/**
 * Verifica se o campo placa está em formato válid
 * 
 * @author Daniel Ferraz
 * @since 11 de ago de 2017
 *
 */
@Component
public class PlacaValidador implements Validador<Carro> {

	@Override
	public void estaValido(Carro carro) {
		if (StringUtils.isEmpty(carro.getPlaca())) {
			throw new NegocioException("Carro sem campo placa");
		}
		carro.setPlaca(carro.getPlaca().toUpperCase());
		if (!carro.getPlaca().matches("^[A-Z]{3}\\d{4}$")) {
			throw new NegocioException("Placa fora de formato padronizado");
		}
	}

}
