/**
 * 
 */
package com.semnome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Entity
@Data
public class Carro {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Getter
	@Column(length=100)
	private String modelo;
	
	@Getter
	@Column(length=7)
	private String placa;

}
