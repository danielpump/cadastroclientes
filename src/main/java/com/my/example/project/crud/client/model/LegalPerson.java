/**
 * 
 */
package com.my.example.project.crud.client.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents a legal person on system model.<br>
 * 
 * @author Daniel Ferraz
 * @since 3 de set de 2017
 *
 */
@Entity
@DiscriminatorValue("LP")
public class LegalPerson extends Person {

}
