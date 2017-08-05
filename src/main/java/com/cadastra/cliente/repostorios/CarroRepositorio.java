/**
 * 
 */
package com.cadastra.cliente.repostorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cadastra.cliente.modelo.Carro;

/**
 * Repositório para o acesso aos dados de ações para um {@link Carro}
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
public interface CarroRepositorio extends CrudRepository<Carro, Long> {
	
	Carro findByPlaca(String placa);

}
