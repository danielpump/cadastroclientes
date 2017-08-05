/**
 * 
 */
package com.semnome.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semnome.model.Pessoa;
import com.semnome.model.PessoaFisica;
import com.semnome.servicos.PessoaService;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(path="/")
	public String home() {
		return "Oi";
	}

	@RequestMapping(path="/pessoa/fisica/salvar", method=RequestMethod.POST)
	public String cadastra(@RequestBody PessoaFisica pessoa) {
		
		//Verificar se tipo está correto
		
		pessoaService.grava(pessoa);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/pessoa/lista")
	public List<Pessoa> lista(){
		return pessoaService.listaTudo();
	}
	
}
