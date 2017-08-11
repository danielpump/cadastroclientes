/**
 * 
 */
package com.cadastra.cliente.servicos.validadores.carro;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.ErroInternoException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.servicos.validadores.Validador;
import com.rest.client.integration.PlacaCarroIntegracao;

/**
 * Realiza a validação do status do carro no serviço de cadastro de status.<br>
 * Caso o serviço não esteja disponivel o sistema indica o veiculo com status
 * {@link Carro.STATUS_NE}
 * 
 * 
 * @author Daniel Ferraz
 * @since 11 de ago de 2017
 *
 */
@Component
public class ValidaStatusCarro implements Validador<Carro> {

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
		} catch (RuntimeException e) {
			if (e.getCause().getMessage().contains("NegocioException")) {
				status = Carro.STATUS_NE;
			} else {
				throw new ErroInternoException(e.getMessage());
			}
		} catch (Exception e) {
			throw new ErroInternoException(e.getMessage());
		}

		if (StringUtils.isEmpty(status)) {
			status = Carro.STATUS_NE;
		}
		entidade.atualizarStatus(status);

	}
}
