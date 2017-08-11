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
 * Implementa��o dos servi�os que a aplica��o disponibilza e suas valida��es
 * 
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

	/**
	 * Realiza o cadastro/atualiza��o de uma pessoa caso todos os seus dados estejam v�lidos 
	 * 
	 * @param pessoa dados da pessoa
	 * @return Dados da pessoa cadastrada
	 * @exception NegocioException Erro de neg�cio ocorrido no servi�o
	 */
	public Pessoa gravar(Pessoa pessoa) {

		pessoaValidador.validar(pessoa);

		return pessoaRepository.save(pessoa);

	}

	/**
	 * Busca uma pessoa pelo c�digo do documento
	 * 
	 * @param documento Documento para consulta
	 * @return Dados da pessoa consultada
	 * @exception NegocioException Erro de neg�cio ocorrido no servi�o
	 */
	public Pessoa buscarPorDocumento(String documento) {
		Pessoa pessoa = pessoaRepository.findByDocumento(documento);
		if (pessoa == null) {
			throw new NegocioException("Registro de pessoa sem cadastro no banco de dados");
		}
		return pessoa;
	}

	/**
	 * Exclui uma pessoa pelo c�digo do documento
	 * 
	 * @param documento Documento para excluir
	 * @return Dados da pessoa excluida
	 * @exception NegocioException Erro de neg�cio ocorrido no servi�o
	 */
	public Pessoa excluirPorDocumento(String documento) {
		Pessoa pessoa = buscarPorDocumento(documento);
		pessoaRepository.delete(pessoa);
		return pessoa;
	}

	/**
	 * Atualiza uma pessoa pelo c�digo do documento
	 *
	 * 
	 * @param documento Documento para atualizar
	 * @param novosDados Novos dados para serem atualizados
	 * @return Dados da pessoa consultada
	 * @exception NegocioException Erro de neg�cio ocorrido no servi�o
	 */
	public Pessoa atualizarPorDocumento(String documento, Pessoa novosDados) {
		Pessoa pessoa = buscarPorDocumento(documento);
		pessoa.atualizar(novosDados);
		gravar(pessoa);
		return pessoa;
	}

}
