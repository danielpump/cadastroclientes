/**
 * 
 */
package com.cadastra.cliente.repostorios;

import org.springframework.data.repository.CrudRepository;

import com.cadastra.cliente.model.Pessoa;

/**
 * Reposit�rio para o acesso aos dados de a��es para uma {@link Pessoa}
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	Pessoa findByDocumento(String documento);

}
