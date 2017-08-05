/**
 * 
 */
package com.semnome.servicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semnome.model.Pessoa;
import com.semnome.repostorios.PessoaRepository;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public void grava(Pessoa pessoa) {

		if(StringUtils.isEmpty(pessoa.getNome())){
			
		}
		
		pessoaRepository.save(pessoa);
	}

	public List<Pessoa> listaTudo() {
		
		ArrayList<Pessoa> pessoas = new ArrayList<>();		
		for (Pessoa pessoa : pessoaRepository.findAll()) {
			pessoas.add(pessoa);
		}		
		return pessoas; 
	}

}
