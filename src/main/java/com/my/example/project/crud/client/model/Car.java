/**
 * 
 */
package com.my.example.project.crud.client.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a car on system model.
 * 
 * @author Daniel Ferraz
 * @since 3 de set de 2017
 *
 */
@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String model;
	private String plate;

	public Long getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public String getPlate() {
		return plate;
	}

}
