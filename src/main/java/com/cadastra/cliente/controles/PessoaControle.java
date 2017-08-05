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
	public String cadastra(@RequestBody PessoaFisica pessoa) {
				
		pessoaService.grava(pessoa);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/pessoa/juridica/salvar", method=RequestMethod.POST)
	public String cadastra(@RequestBody PessoaJuridica pessoa) {
				
		pessoaService.grava(pessoa);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/pessoa/busca", params="documento")
	public Pessoa lista(@RequestParam String documento){
		return pessoaService.buscaPorDocumento(documento);
	}
	
	@RequestMapping(path="/pessoa/exclui", params="documento")
	public Pessoa exclui(@RequestParam String documento){
		return pessoaService.excluiPorDocumento(documento);
	}
	
	
	
}