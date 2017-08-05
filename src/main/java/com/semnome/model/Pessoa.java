package com.semnome.model;

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

import lombok.Getter;

/**
 * Representa a entidade pessoa da aplicação<br>
 * Esta classe deve ser especializada para ser utilizada<br>
 * Tipos disponiveis:<br>
 * Pessoa fisica: {@link PessoaFisica}<br>
 * Pessoa juridica: {@link PessoaJuridica}<br>
 * 
 * @author Daniel Ferraz
 * @since 5 de ago de 2017
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class Pessoa {
	
	/**
	 * Código de pessoa fisica
	 */
	public static final String PESSOA_FISICA = "PF";
	/**
	 * Código de pessoa juridica
	 */
	public static final String PESSOA_JURIDICA = "PJ"; 
	
	@Getter
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Getter	
	@Column(length=100)
	private String nome;
	
	@Getter
	@Column(length=14)
	private String documento;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Carro> carros;

}
