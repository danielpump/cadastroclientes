/**
 * 
 */
package com.cadastra.cliente.modelo;

/**
 * Representa que a entidade pertence ao dominio de neg�cio da aplica��o
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
public interface Entidade {

	/**
	 * Atualiza os campos da entidade conforme a necessidade especifica
	 * 
	 * @param entidade
	 */
	void atualizar(Entidade entidade);
	
}
