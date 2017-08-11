/**
 * 
 */
package com.cadastra.cliente.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entidade que representa uma pessoa juridica na aplicação
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Entity
@DiscriminatorValue(value = "PJ")
public class PessoaJuridica extends Pessoa {

}
