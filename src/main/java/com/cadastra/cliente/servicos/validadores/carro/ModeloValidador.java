package com.cadastra.cliente.servicos.validadores.carro;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.servicos.validadores.Validador;

@Component
public class ModeloValidador implements Validador<Carro> {

	@Override
	public void estaValido(Carro carro) {
		if (StringUtils.isEmpty(carro.getModelo())) {
			throw new NegocioException("Carro nao modelo");
		}
	}

}
