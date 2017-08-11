/**
 * 
 */
package com.cadastra.cliente.servicos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.repostorios.CarroRepositorio;
import com.cadastra.cliente.repostorios.PessoaRepositorio;
import com.cadastra.cliente.servicos.validadores.PessoaValidador;

/**
 * Implementação dos serviços que a aplicação disponibilza e suas validações
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Service
public class PessoaServico {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private CarroRepositorio carroRepositorio;

	@Autowired
	private PessoaValidador pessoaValidador;
	
	private List<String> statusValidos = Arrays.asList(Carro.STATUS_OK,Carro.STATUS_NE,Carro.STATUS_ROUBADO,Carro.STATUS_APREENDIDO);

	/**
	 * Realiza o cadastro/atualização de uma pessoa caso todos os seus dados estejam válidos 
	 * 
	 * @param pessoa dados da pessoa
	 * @return Dados da pessoa cadastrada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	public Pessoa gravar(Pessoa pessoa) {

		pessoaValidador.validar(pessoa);

		return pessoaRepositorio.save(pessoa);

	}

	/**
	 * Busca uma pessoa pelo código do documento
	 * 
	 * @param documento Documento para consulta
	 * @return Dados da pessoa consultada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	public Pessoa buscarPorDocumento(String documento) {
		Pessoa pessoa = pessoaRepositorio.findByDocumento(documento);
		if (pessoa == null) {
			throw new NegocioException("Registro de pessoa sem cadastro no banco de dados");
		}
		return pessoa;
	}

	/**
	 * Exclui uma pessoa pelo código do documento
	 * 
	 * @param documento Documento para excluir
	 * @return Dados da pessoa excluida
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	public Pessoa excluirPorDocumento(String documento) {
		Pessoa pessoa = buscarPorDocumento(documento);
		pessoaRepositorio.delete(pessoa);
		return pessoa;
	}

	/**
	 * Atualiza uma pessoa pelo código do documento
	 *
	 * 
	 * @param documento Documento para atualizar
	 * @param novosDados Novos dados para serem atualizados
	 * @return Dados da pessoa consultada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	public Pessoa atualizarPorDocumento(String documento, Pessoa novosDados) {
		Pessoa pessoa = buscarPorDocumento(documento);
		pessoa.atualizar(novosDados);
		gravar(pessoa);
		return pessoa;
	}
	
	/**
	 * Atualiza uma pessoa pelo código do documento
	 * 
	 * @param documento Documento para atualizar
	 * @return Dados da pessoa atualizada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	public Pessoa atualizarDadosNoServicoPorDocumento(String documento) {
		Pessoa pessoa = buscarPorDocumento(documento);		
		gravar(pessoa);
		return pessoa;
	}
	
	/**
	 * Consulta a quantidade de veiculos em um status
	 * 
	 * @param documento Status para consultar
	 * @return Quantidade de registros do tipo
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	public Long[] consultarQuantidadeCarrosPorStatus(String status) {
		status = status.toUpperCase();
		boolean ehValido = false;
		for (String statusValido : statusValidos) {
			if(statusValido.equals(status))
				ehValido = true;			
		}
		if(!ehValido)
			throw new NegocioException("Status para consulta incorreto");
		
		Long quantidadePessoaFisica = carroRepositorio.countByStatusAndTipoPessoa(status, Pessoa.PESSOA_FISICA);
		Long quantidadePessoaJuridica = carroRepositorio.countByStatusAndTipoPessoa(status, Pessoa.PESSOA_JURIDICA);
		
		return new Long[]{quantidadePessoaFisica, quantidadePessoaJuridica};
	}

}
