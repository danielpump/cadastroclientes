/**
 * 
 */
package com.semnome.servicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			throw new RuntimeException("Nome deve ser preenchido");
		}

		if (StringUtils.isEmpty(pessoa.getDocumento())) {
			throw new RuntimeException("Documento deve ser preenchido");
		}

		if (!pessoa.getDocumento().matches("[0-9]+")) {
			throw new RuntimeException("Documento deve possuir apenas numeros");
		}

		if (pessoa.getDocumento().length() != 11) {
			throw new RuntimeException("Documento deve conter os 11 caracteres do CPF");
		}

		if (pessoa.getCarros().isEmpty()) {
			throw new RuntimeException("Pessoa deve possuir ao menos um veículo para cadastro");
		}

		for (Carro carro : pessoa.getCarros()) {
			if (StringUtils.isEmpty(carro.getModelo())) {
				throw new RuntimeException("Carro nao modelo");
			}
			if (StringUtils.isEmpty(carro.getPlaca())) {
				throw new RuntimeException("Carro nao placa");
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
