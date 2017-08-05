/**
 * 
 */
package com.semnome.model;

import javax.persistence.DiscriminatorValue;

/**
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@DiscriminatorValue(value = "PF")
public class PessoaFisica extends Pessoa {

}
