/**
 * 
 */
package com.semnome.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Entity
@DiscriminatorValue(value = "PJ")
public class PessoaJuridica extends Pessoa {

}
