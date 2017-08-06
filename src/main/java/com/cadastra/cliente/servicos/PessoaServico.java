/**
 * 
 */
package com.cadastra.cliente.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.repostorios.PessoaRepositorio;
import com.cadastra.cliente.servicos.validadores.PessoaValidador;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Service
public class PessoaServico {

	@Autowired
	private PessoaRepositorio pessoaRepository;

	@Autowired
	private PessoaValidador pessoaValidador;

	public void gravar(Pessoa pessoa) {

		pessoaValidador.validar(pessoa);

		pessoaRepository.save(pessoa);

	}

	public Pessoa buscarPorDocumento(String documento) {
		Pessoa pessoa = pessoaRepository.findByDocumento(documento);
		if (pessoa == null) {
			throw new NegocioException("Registro não encontrado");
		}
		return pessoa;
	}

	public Pessoa excluirPorDocumento(String documento) {
		Pessoa pessoa = buscarPorDocumento(documento);
		pessoaRepository.delete(pessoa);
		return pessoa;
	}

	public Pessoa atualizarPorDocumento(String documento, Pessoa novosDados) {
		Pessoa pessoa = buscarPorDocumento(documento);
		pessoa.atualizar(novosDados);
		gravar(pessoa);
		return pessoa;
	}

}
