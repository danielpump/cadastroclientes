package com.cadastra.cliente.servicos.validadores.carro;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.servicos.validadores.Validador;

@Component
public class PlacaValidador implements Validador<Carro> {

	@Override
	public void estaValido(Carro carro) {
		if (StringUtils.isEmpty(carro.getPlaca())) {
			throw new NegocioException("Carro nao placa");
		}
		if (!carro.getPlaca().matches("^[A-Z]{3}\\d{4}$")) {
			throw new NegocioException("Formato de placa inválida");
		}
	}

}
