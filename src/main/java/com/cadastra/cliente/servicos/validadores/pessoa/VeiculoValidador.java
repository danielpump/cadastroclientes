package com.cadastra.cliente.servicos.validadores.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.servicos.validadores.CarroValidador;
import com.cadastra.cliente.servicos.validadores.Validador;

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
		if (pessoa.getCarros().isEmpty()) {
			throw new NegocioException("Pessoa deve possuir ao menos um veículo para cadastro");
		}

		for (Carro carro : pessoa.getCarros()) {
			carro.setPlaca(carro.getPlaca().toUpperCase());
			validador.validar(carro);
		}

	}

}
