/**
 * 
 */
package com.cadastra.cliente.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Entity
@DiscriminatorValue(value = "PF")
public class PessoaFisica extends Pessoa {

}
