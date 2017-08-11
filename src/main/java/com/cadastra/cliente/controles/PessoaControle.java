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

import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.modelo.PessoaFisica;
import com.cadastra.cliente.modelo.PessoaJuridica;
import com.cadastra.cliente.servicos.PessoaServico;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@RestController
public class PessoaControle {
	
	@Autowired
	private PessoaServico pessoaService;

	@RequestMapping(path="/pessoa/fisica/cadastrar", method=RequestMethod.POST)
	public Pessoa cadastrar(@RequestBody PessoaFisica pessoa) {		
		return pessoaService.gravar(pessoa);
	}
	
	@RequestMapping(path="/pessoa/juridica/cadastrar", method=RequestMethod.POST)
	public Pessoa cadastrar(@RequestBody PessoaJuridica pessoa) {
		return pessoaService.gravar(pessoa);
	}
	
	@RequestMapping(path="/pessoa/fisica/atualizar", method=RequestMethod.POST)
	public Pessoa atualizar(@RequestParam String documento, @RequestBody PessoaFisica pessoa) {
		return pessoaService.atualizarPorDocumento(documento, pessoa);
	}
	
	@RequestMapping(path="/pessoa/juridica/atualizar", method=RequestMethod.POST)
	public Pessoa atualizar(@RequestParam String documento, @RequestBody PessoaJuridica pessoa) {
		return pessoaService.atualizarPorDocumento(documento, pessoa);
	}
	
	@RequestMapping(path="/pessoa/buscar", params="documento", method=RequestMethod.GET)
	public Pessoa listar(@RequestParam String documento){
		return pessoaService.buscarPorDocumento(documento);
	}
	
	@RequestMapping(path="/pessoa/excluir", params="documento" , method=RequestMethod.DELETE)
	public Pessoa excluir(@RequestParam String documento){
		return pessoaService.excluirPorDocumento(documento);
	}

	
}
