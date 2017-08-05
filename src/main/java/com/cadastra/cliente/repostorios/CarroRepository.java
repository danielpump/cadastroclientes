/**
 * 
 */
package com.cadastra.cliente.repostorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cadastra.cliente.model.Carro;

/**
 * Reposit�rio para o acesso aos dados de a��es para um {@link Carro}
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
public interface CarroRepository extends CrudRepository<Carro, Long> {
	
	Carro findByPlaca(String placa);

}
