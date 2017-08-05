/**
 * 
 */
package com.semnome.servicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semnome.excecoes.NegocioException;
import com.semnome.model.Carro;
import com.semnome.model.Pessoa;
import com.semnome.repostorios.PessoaRepository;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public void grava(Pessoa pessoa) {

		if (StringUtils.isEmpty(pessoa.getNome())) {
			throw new NegocioException("Nome deve ser preenchido");
		}

		if (StringUtils.isEmpty(pessoa.getDocumento())) {
			throw new NegocioException("Documento deve ser preenchido");
		}

		if (!pessoa.getDocumento().matches("[0-9]+")) {
			throw new NegocioException("Documento deve possuir apenas numeros");
		}

		if(Pessoa.PESSOA_FISICA.equals(pessoa.getTipoPessoa())) {
			if (pessoa.getDocumento().length() != 11) {
				throw new NegocioException("Documento deve conter os 11 caracteres do CPF");
			}
		} else if(Pessoa.PESSOA_JURIDICA.equals(pessoa.getTipoPessoa())) {
			if (pessoa.getDocumento().length() != 14) {
				throw new NegocioException("Documento deve conter os 14 caracteres do CNPJ");
			}
		} else {
			throw new NegocioException("Tipo de pessoa não identificado");
		}
		

		if (pessoa.getCarros().isEmpty()) {
			throw new NegocioException("Pessoa deve possuir ao menos um veículo para cadastro");
		}

		for (Carro carro : pessoa.getCarros()) {
			if (StringUtils.isEmpty(carro.getModelo())) {
				throw new NegocioException("Carro nao modelo");
			}
			if (StringUtils.isEmpty(carro.getPlaca())) {
				throw new NegocioException("Carro nao placa");
			}
		}

		pessoaRepository.save(pessoa);
	}

	public List<Pessoa> listaTudo() {

		ArrayList<Pessoa> pessoas = new ArrayList<>();
		for (Pessoa pessoa : pessoaRepository.findAll()) {
			pessoas.add(pessoa);
		}
		return pessoas;
	}

}
