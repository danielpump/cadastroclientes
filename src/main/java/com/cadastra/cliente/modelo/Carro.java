/**
 * 
 */
package com.cadastra.cliente.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Data;

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
	
	public static final String STATUS_OK = "OK";
	public static final String STATUS_NE = "NE";
	public static final String STATUS_ROUBADO = "ROUBADO";
	public static final String STATUS_APREENDIDO = "APREENDIDO";
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@Column(length = 100)
	private String modelo;

	@Column(length = 7, unique=true)
	private String placa;
	
	@Column(length = 10)
	private String status;
	
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataValidacao;
	
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
	
	
	/**
	 * Atualiza o status e registra o momento em que a atualização foi feita
	 * 
	 * @param status
	 */
	public void atualizarStatus(String status) {

		this.status = status;
		this.dataValidacao = LocalDateTime.now();
	}

}
