/**
 * 
 */
package com.cadastra.cliente.repostorios;

import org.springframework.data.repository.CrudRepository;

import com.cadastra.cliente.model.Pessoa;

/**
 * Repositório para o acesso aos dados de ações para uma {@link Pessoa}
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	Pessoa findByDocumento(String documento);

}
