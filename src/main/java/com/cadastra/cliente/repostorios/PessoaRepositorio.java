/**
 * 
 */
package com.cadastra.cliente.repostorios;

import org.springframework.data.repository.CrudRepository;

import com.cadastra.cliente.modelo.Pessoa;

/**
 * Repositório para o acesso aos dados de ações para uma {@link Pessoa}
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
public interface PessoaRepositorio extends CrudRepository<Pessoa, Long> {
	
	/**
	 * Busca uma pessoa pelo documento
	 * 
	 * @param documento
	 * @return
	 */
	Pessoa findByDocumento(String documento);
	
	

}
