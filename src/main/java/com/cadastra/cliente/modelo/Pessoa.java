package com.cadastra.cliente.modelo;

import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@DiscriminatorColumn(name = "TIPOPESSOA")
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
	@JsonIgnore
	private Long id;
	
	@Getter	
	@Column(length=100)
	private String nome;
	
	@Getter
	@Column(length=14, unique=true)
	private String documento;
	
	@Getter
	@Column(length=2, insertable=false, updatable=false, name="TIPOPESSOA")
	private String tipoPessoa;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="idpessoa")
	@Getter
	private List<Carro> carros;
	
	public static Pessoa criaPessoaFisica() {
		return new PessoaFisica();
	}
	
	public static Pessoa criaPessoaJuridica() {
		return new PessoaJuridica();
	}
	
	public Pessoa comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Pessoa comDocumento(String documento) {
		this.documento = documento;
		return this;
	}
	
	public Pessoa adicionaCarro(Carro carro) {
		if(this.carros == null) this.carros = new ArrayList<>();
		this.carros.add(carro);
		return this;
	}
	

}
