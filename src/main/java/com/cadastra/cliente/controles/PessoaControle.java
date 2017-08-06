/**
 * 
 */
package com.cadastra.cliente.controles;

import java.util.List;

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

	@RequestMapping(path="/pessoa/fisica/salvar", method=RequestMethod.POST)
	public String cadastrar(@RequestBody PessoaFisica pessoa) {
				
		pessoaService.gravar(pessoa);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/pessoa/juridica/salvar", method=RequestMethod.POST)
	public String cadastrar(@RequestBody PessoaJuridica pessoa) {
				
		pessoaService.gravar(pessoa);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/pessoa/fisica/atualizar", method=RequestMethod.POST)
	public String atualizar(@RequestParam String documento, @RequestBody PessoaFisica pessoa) {
				
		pessoaService.atualizarPorDocumento(documento, pessoa);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/pessoa/juridica/atualizar", method=RequestMethod.POST)
	public String atualizar(@RequestParam String documento, @RequestBody PessoaJuridica pessoa) {
				
		pessoaService.atualizarPorDocumento(documento, pessoa);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/pessoa/buscar", params="documento")
	public Pessoa listar(@RequestParam String documento){
		return pessoaService.buscarPorDocumento(documento);
	}
	
	@RequestMapping(path="/pessoa/excluir", params="documento")
	public Pessoa excluir(@RequestParam String documento){
		return pessoaService.excluirPorDocumento(documento);
	}

	
}
