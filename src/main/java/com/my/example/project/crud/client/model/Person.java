package com.my.example.project.crud.client.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

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
	@JsonSubTypes.Type(name="PF", value=NaturalPerson.class),
	@JsonSubTypes.Type(name="PJ", value=LegalPerson.class)
})
public abstract class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String document;
	
	@Column(updatable=false, insertable=false)
	private String type;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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

	public String getType() {
		return type;
	}
	
}
