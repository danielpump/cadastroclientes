/**
 * 
 */
package com.cadastra.cliente.servicos.validadores;

import com.cadastra.cliente.modelo.Entidade;

/**
 * Interface dos validadores
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
public interface Validador<E extends Entidade> {
	
	/**
	 * Identifica se a entidade está passando na validação implementada
	 * 
	 * @param entidade
	 */
	void estaValido(E entidade);
	
	/**
	 * Prioridade de validação dos itens quanto menor o numero mais prioridade a validação terá
	 * 
	 * @return
	 */
	default Integer prioridade() {
		return 0;
	}
		

}
