package com.cadastra.cliente.servicos.validadores.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.repostorios.PessoaRepositorio;
import com.cadastra.cliente.servicos.validadores.Validador;

/**
 * Verifica se o registro da pessoa já existe no banco de dados<br>
 * Caso seja uma atualização essa validação é ignorada
 * 
 * @author Daniel Ferraz
 * @since 11 de ago de 2017
 *
 */
@Component
public class PessoaJaExisteValidador implements Validador<Pessoa> {

	@Autowired
	private PessoaRepositorio pessoaRepository;

	@Override
	public Integer prioridade() {
		return 1;
	}

	@Override
	public void estaValido(Pessoa pessoa) {
		if (pessoa.getId() == null) {
			if (pessoaRepository.findByDocumento(pessoa.getDocumento()) != null) {
				throw new NegocioException("Registro de pessoa existente na base");
			}
		}
	}

}
