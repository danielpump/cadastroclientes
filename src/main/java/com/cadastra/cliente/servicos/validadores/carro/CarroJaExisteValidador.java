package com.cadastra.cliente.servicos.validadores.carro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.repostorios.CarroRepositorio;
import com.cadastra.cliente.servicos.validadores.Validador;

/**
 * Verifica se o carro j� est� cadastrado na base<br>
 * Caso seja uma inclus�o e j� esteja cadastrado retorna um erro<br>
 * Caso seja uma atualiza��o verifica se o carro est� vinculado a outro proprieta�rio, caso esteja ent�o lan�a uma exce��o.
 * 
 * @author Daniel Ferraz
 * @since 11 de ago de 2017
 *
 */
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
		Carro carregado = carroRepository.findByPlaca(carro.getPlaca());		
		if (carregado != null) {
			if(carro.getPessoa() == null) {
				throw new NegocioException("Registro de carro existente na base");
			} else if (!carro.getPessoa().getId().equals(carregado.getPessoa().getId())) {
				throw new NegocioException("Carro cadastrado em nome de outra pessoa");
			}
		}
	}

}
