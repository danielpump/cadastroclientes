/**
 * 
 */
package com.cadastra.cliente.servicos.validadores;

import com.cadastra.cliente.modelo.Entidade;

/**
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
public interface Validador<E extends Entidade> {
	
	void estaValido(E entidade);
	
	default Integer prioridade() {
		return 0;
	}
		

}
