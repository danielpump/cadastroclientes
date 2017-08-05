package com.cadastra.cliente.servicos.validadores.pessoa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.servicos.validadores.Validador;

@Component
public class NomeValidador implements Validador<Pessoa> {

	@Override
	public void estaValido(Pessoa pessoa) {
		if (StringUtils.isEmpty(pessoa.getNome())) {
			throw new NegocioException("Nome deve ser preenchido");
		}
	}

}
