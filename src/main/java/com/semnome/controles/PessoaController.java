/**
 * 
 */
package com.semnome.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.semnome.model.Pessoa;
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

	@RequestMapping(path="/pessoa/grava", method=RequestMethod.POST)
	public String cadastra(Pessoa pessoa) {
		
//		Carro carro = new Carro();
//		
//		carro.setModelo("Teste");
//		carro.setPlaca("AAA8899");
		
		//Pessoa pessoa = Pessoa.criaPessoaFisica().comNome("Daniel").comDocumento("12378945652").adicionaCarro(carro);
		
		pessoaService.grava(pessoa);
		
		return "Sucesso";
	}
	
	@RequestMapping(path="/pessoa/lista")
	public List<Pessoa> lista(){
		return pessoaService.listaTudo();
	}
	
}
