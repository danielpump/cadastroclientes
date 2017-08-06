package com.cadastra.cliente.servicos.validadores.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.repostorios.PessoaRepositorio;
import com.cadastra.cliente.servicos.validadores.Validador;

@Component
public class PessoaJaExisteValidador implements Validador<Pessoa> {

	@Autowired
	private PessoaRepositorio pessoaRepository;

	@Override
	public Integer prioridade() {
		return 1;
	}

	@Override
	public void estaValido(Pessoa pessoa) {
		if (pessoa.getId() == null) {
			if (pessoaRepository.findByDocumento(pessoa.getDocumento()) != null) {
				throw new NegocioException("Registro já existe");
			}
		}
	}

}
