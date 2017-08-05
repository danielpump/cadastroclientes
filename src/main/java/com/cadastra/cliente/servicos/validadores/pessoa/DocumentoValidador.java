package com.cadastra.cliente.servicos.validadores.pessoa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.servicos.validadores.Validador;

@Component
public class DocumentoValidador implements Validador<Pessoa> {

	@Override
	public void estaValido(Pessoa pessoa) {
		if (StringUtils.isEmpty(pessoa.getNome())) {
			if (StringUtils.isEmpty(pessoa.getDocumento())) {
				throw new NegocioException("Documento deve ser preenchido");
			}

			if (!pessoa.getDocumento().matches("[0-9]+")) {
				throw new NegocioException("Documento deve possuir apenas numeros");
			}

			if (Pessoa.PESSOA_FISICA.equals(pessoa.getTipoPessoa())) {
				if (pessoa.getDocumento().length() != 11) {
					throw new NegocioException("Documento deve conter os 11 caracteres do CPF");
				}
			} else if (Pessoa.PESSOA_JURIDICA.equals(pessoa.getTipoPessoa())) {
				if (pessoa.getDocumento().length() != 14) {
					throw new NegocioException("Documento deve conter os 14 caracteres do CNPJ");
				}
			} else {
				throw new NegocioException("Tipo de pessoa não identificado");
			}
		}
	}

}
