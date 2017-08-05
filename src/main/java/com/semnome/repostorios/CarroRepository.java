/**
 * 
 */
package com.semnome.repostorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.semnome.model.Carro;

/**
 * Repositório para as ações de um veículo
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
//@Repository
public interface CarroRepository extends CrudRepository<Carro, Long> {

}
