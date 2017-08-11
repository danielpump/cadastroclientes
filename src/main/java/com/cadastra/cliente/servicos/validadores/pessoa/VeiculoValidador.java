package com.cadastra.cliente.servicos.validadores.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.servicos.validadores.CarroValidador;
import com.cadastra.cliente.servicos.validadores.Validador;

/**
 * Verifica se os dados dos veiculos da pessoa estão válidos
 * 
 * @author Daniel Ferraz
 * @since 11 de ago de 2017
 *
 */
@Component
public class VeiculoValidador implements Validador<Pessoa> {

	@Autowired
	private CarroValidador validador;
	
	@Override
	public Integer prioridade() {
		return 2;
	}

	@Override
	public void estaValido(Pessoa pessoa) {
		if (pessoa.getCarros() == null || pessoa.getCarros().isEmpty()) {
			throw new NegocioException("Pessoa deve possuir ao menos um carro para cadastro");
		}

		for (Carro carro : pessoa.getCarros()) {			
			validador.validar(carro);
		}

	}

}
