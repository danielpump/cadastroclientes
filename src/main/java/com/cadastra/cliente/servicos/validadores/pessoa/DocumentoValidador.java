package com.cadastra.cliente.servicos.validadores.pessoa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.modelo.PessoaFisica;
import com.cadastra.cliente.modelo.PessoaJuridica;
import com.cadastra.cliente.servicos.validadores.Validador;

/**
 * Verifica se o campo documento e tipo pessoa estão validos, tanto para pessoa física quanto juridica
 * 
 * @author Daniel Ferraz
 * @since 11 de ago de 2017
 *
 */
@Component
public class DocumentoValidador implements Validador<Pessoa> {

	@Override
	public void estaValido(Pessoa pessoa) {
		if (StringUtils.isEmpty(pessoa.getDocumento())) {
			throw new NegocioException("Documento deve ser preenchido");
		}

		if (!pessoa.getDocumento().matches("[0-9]+")) {
			throw new NegocioException("Documento deve possuir apenas numeros");
		}
		if(StringUtils.isEmpty(pessoa.getTipoPessoa())) {
			throw new NegocioException("Tipo de pessoa deve ser preenchido");
		}
		pessoa.upperCaseTipoPessoa();
		if (Pessoa.PESSOA_FISICA.equals(pessoa.getTipoPessoa()) && PessoaFisica.class.equals(pessoa.getClass())) {
			if (pessoa.getDocumento().length() != 11) {
				throw new NegocioException("Documento deve conter os 11 caracteres do CPF");
			}
		} else if (Pessoa.PESSOA_JURIDICA.equals(pessoa.getTipoPessoa()) && PessoaJuridica.class.equals(pessoa.getClass())) {
			if (pessoa.getDocumento().length() != 14) {
				throw new NegocioException("Documento deve conter os 14 caracteres do CNPJ");
			}
		} else {
			throw new NegocioException("Tipo de pessoa indeterminado");
		}
	}

}
