/**
 * 
 */
package com.cadastra.cliente.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;

/**
 * Representa a entidade carro da aplicação
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Entity
@Data
public class Carro implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@Column(length = 100)
	private String modelo;

	@Column(length = 7)
	private String placa;
	
	@ManyToOne
	@JoinColumn(name = "idpessoa", nullable=false, insertable=false, updatable=false)
	@JsonIgnore
	private Pessoa pessoa;

	@Override
	public void atualizar(Entidade entidade) {
		Carro carro = (Carro) entidade;

		this.modelo = carro.getModelo();
		this.placa = carro.getPlaca();
	}

}
