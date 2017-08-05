package com.semnome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;

@Entity
public abstract class Pessoa {
	
	@Getter
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Getter
	private String nome;
	
	@Getter
	private String documento;

}
