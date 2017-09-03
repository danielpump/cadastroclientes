package com.my.example.project.crud.client.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

/**
 * Represents a generic person on system model.<br>
 * Has two main implementations {@code LegalPerson} and {@code NaturalPerson}.
 * 
 * @author Daniel Ferraz
 * @since 3 de set de 2017
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property="type", include=As.PROPERTY)
@JsonSubTypes({
	@JsonSubTypes.Type(name="NP", value=NaturalPerson.class),
	@JsonSubTypes.Type(name="LP", value=LegalPerson.class)
})
public abstract class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String document;
	
	@OneToMany
	private List<Car> cars;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDocument() {
		return document;
	}

	public List<Car> getCars() {
		return cars;
	}
	
	

}
