/**
 * 
 */
package com.cadastra.cliente.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.modelo.PessoaFisica;
import com.cadastra.cliente.modelo.PessoaJuridica;
import com.cadastra.cliente.servicos.PessoaServico;

/**
 * Controlador das requisições e serviços que a aplicação disponibiliza para pessoas
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@RestController
public class PessoaControle {
	
	@Autowired
	private PessoaServico pessoaService;

	/**
	 * URL: {@code http://localhost:8080/pessoa/fisica/cadastrar}<br>
	 * 
	 * Serviço de cadastro de pessoa fisica
	 * 
	 * @param pessoa Dados da pessoa
	 * @return Dados da pessoa cadastrada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/fisica/cadastrar", method=RequestMethod.POST)
	public Pessoa cadastrar(@RequestBody PessoaFisica pessoa) {		
		return pessoaService.gravar(pessoa);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/juridica/cadastrar}<br>
	 * 
	 * Serviço de cadastro de pessoa juridica
	 *
	 * @param pessoa Dados da pessoa
	 * @return Dados da pessoa cadastrada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/juridica/cadastrar", method=RequestMethod.POST)
	public Pessoa cadastrar(@RequestBody PessoaJuridica pessoa) {
		return pessoaService.gravar(pessoa);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/fisica/atualizar?documento=documento}<br>
	 * 
	 * Serviço de atualização de pessoa fisica
	 * 
	 * @param documento Código do documento
	 * @param pessoa Dados da pessoa
	 * @return Dados da pessoa atualizada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/fisica/atualizar", method=RequestMethod.POST)
	public Pessoa atualizar(@RequestParam String documento, @RequestBody PessoaFisica pessoa) {
		return pessoaService.atualizarPorDocumento(documento, pessoa);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/juridica/atualizar?documento=documento}<br>
	 * 
	 * Serviço de atualização de pessoa juridica
	 * 
	 * @param documento Código do documento
	 * @param pessoa Dados da pessoa
	 * @return Dados da pessoa atualizada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/juridica/atualizar", method=RequestMethod.POST)
	public Pessoa atualizar(@RequestParam String documento, @RequestBody PessoaJuridica pessoa) {
		return pessoaService.atualizarPorDocumento(documento, pessoa);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/buscar?documento=documento}<br>
	 * 
	 * Consulta uma pessoa pelo código do documento
	 * 
	 * @param documento Código do documento
	 * @return Dados da pessoa consultada
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/buscar", params="documento", method=RequestMethod.GET)
	public Pessoa listar(@RequestParam String documento){
		return pessoaService.buscarPorDocumento(documento);
	}
	
	/**
	 * URL: {@code http://localhost:8080/pessoa/excluir?documento=documento}<br>
	 * 
	 * Exclui uma pessoa pelo código do documento
	 * 
	 * @param documento Código do documento
	 * @return Dados da pessoa excluida
	 * @exception NegocioException Erro de negócio ocorrido no serviço
	 */
	@RequestMapping(path="/pessoa/excluir", params="documento" , method=RequestMethod.DELETE)
	public Pessoa excluir(@RequestParam String documento){
		return pessoaService.excluirPorDocumento(documento);
	}

	
}
