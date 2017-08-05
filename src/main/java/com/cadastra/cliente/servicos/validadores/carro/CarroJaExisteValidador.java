package com.cadastra.cliente.servicos.validadores.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.repostorios.CarroRepositorio;
import com.cadastra.cliente.servicos.validadores.Validador;

@Component
public class CarroJaExisteValidador implements Validador<Carro> {

	@Autowired
	private CarroRepositorio carroRepository;
	
	@Override
	public Integer prioridade() {
		return 1;
	}

	@Override
	public void estaValido(Carro carro) {
		if (carroRepository.findByPlaca(carro.getPlaca()) != null) {
			throw new NegocioException("Veículo já cadastrado");
		}
	}

}
