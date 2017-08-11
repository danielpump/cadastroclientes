/**
 * 
 */
package com.cadastra.cliente.servicos.validadores;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.modelo.Carro;

/**
 * Controla o fluxo de validação de um {@link Carro}}
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
@Component
public class CarroValidador {

	@Autowired
	private List<Validador<Carro>> validadores;

	@PostConstruct
	public void init() {
		validadores.sort( (validor1, validor2) -> validor1.prioridade().compareTo(validor2.prioridade()) );
	}
	
	public void validar(Carro carro) {
		for (Validador<Carro> validador : validadores) {
			validador.estaValido(carro);
		}
	}

}
