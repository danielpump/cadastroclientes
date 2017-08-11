/**
 * 
 */
package com.cadastra.cliente.servicos.validadores.carro;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.servicos.validadores.Validador;
import com.rest.client.integration.PlacaCarroIntegracao;

/**
 * @author Daniel Ferraz
 * @since 11 de ago de 2017
 *
 */
@Component
public class ValidaStatusCarro  implements Validador<Carro> {
	
	@Autowired
	private PlacaCarroIntegracao integracao;
	
	@Override
	public Integer prioridade() {
		return 3;
	}

	@Override
	public void estaValido(Carro entidade) {

		String status = null;
		
		try {
			Map<String, String> consultarPorPlaca = integracao.consultarPorPlaca(entidade.getPlaca());
			status = consultarPorPlaca.get("status");
		} catch (Exception e) {
			status = Carro.STATUS_NE;
		}
		
		if(StringUtils.isEmpty(status)) {
			status = Carro.STATUS_NE;
		}
		entidade.atualizarStatus(status);
		
	}
}
