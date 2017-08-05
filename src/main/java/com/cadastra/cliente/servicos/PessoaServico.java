/**
 * 
 */
package com.cadastra.cliente.servicos;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cadastra.cliente.excecoes.NegocioException;
import com.cadastra.cliente.modelo.Carro;
import com.cadastra.cliente.modelo.Pessoa;
import com.cadastra.cliente.repostorios.CarroRepositorio;
import com.cadastra.cliente.repostorios.PessoaRepositorio;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Service
public class PessoaServico {

	@Autowired
	private PessoaRepositorio pessoaRepository;
	
	@Autowired
	private CarroRepositorio carroRepository;

	public void grava(Pessoa pessoa) {

		if (StringUtils.isEmpty(pessoa.getNome())) {
			throw new NegocioException("Nome deve ser preenchido");
		}

		if (StringUtils.isEmpty(pessoa.getDocumento())) {
			throw new NegocioException("Documento deve ser preenchido");
		}

		if (!pessoa.getDocumento().matches("[0-9]+")) {
			throw new NegocioException("Documento deve possuir apenas numeros");
		}

		if(Pessoa.PESSOA_FISICA.equals(pessoa.getTipoPessoa())) {
			if (pessoa.getDocumento().length() != 11) {
				throw new NegocioException("Documento deve conter os 11 caracteres do CPF");
			}
		} else if(Pessoa.PESSOA_JURIDICA.equals(pessoa.getTipoPessoa())) {
			if (pessoa.getDocumento().length() != 14) {
				throw new NegocioException("Documento deve conter os 14 caracteres do CNPJ");
			}
		} else {
			throw new NegocioException("Tipo de pessoa não identificado");
		}
				
		if(pessoaRepository.findByDocumento(pessoa.getDocumento()) != null) {
			throw new NegocioException("Registro já existe");
		}
		

		if (pessoa.getCarros().isEmpty()) {
			throw new NegocioException("Pessoa deve possuir ao menos um veículo para cadastro");
		}

		for (Carro carro : pessoa.getCarros()) {
			if (StringUtils.isEmpty(carro.getModelo())) {
				throw new NegocioException("Carro nao modelo");
			}
			if (StringUtils.isEmpty(carro.getPlaca())) {
				throw new NegocioException("Carro nao placa");
			}
			carro.setPlaca(carro.getPlaca().toUpperCase());
			if(!carro.getPlaca().matches("^[A-Z]{3}\\d{4}$")) {
				throw new NegocioException("Formato de placa inválida");
			}
			if(carroRepository.findByPlaca(carro.getPlaca()) != null) {
				throw new NegocioException("Veículo já cadastrado");
			}
		}

		pessoaRepository.save(pessoa);
	}
	
	public Pessoa buscaPorDocumento(String documento) {
		Pessoa pessoa = pessoaRepository.findByDocumento(documento);
		if(pessoa == null) {
			throw new NegocioException("Registro não encontrado"); 
		}
		return pessoa;
	}
	
	public Pessoa excluiPorDocumento(String documento) {
		Pessoa pessoa = buscaPorDocumento(documento);		
		pessoaRepository.delete(pessoa);
		return pessoa;
	}

}
