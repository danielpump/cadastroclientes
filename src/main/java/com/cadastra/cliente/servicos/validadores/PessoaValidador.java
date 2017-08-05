/**
 * 
 */
package com.cadastra.cliente.servicos.validadores;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.modelo.Pessoa;

/**
 * Validadores para uma entidade do tipo {@link Pessoa}
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
@Component
public class PessoaValidador {

	@Autowired
	private List<Validador<Pessoa>> validadores;

	@PostConstruct
	public void init() {
		validadores.sort((validor1, validor2) -> validor1.prioridade().compareTo(validor2.prioridade()));
	}

	public void validar(Pessoa pessoa) {
		for (Validador<Pessoa> validador : validadores) {
			validador.estaValido(pessoa);
		}
	}

}
