package com.pdsi.laissez.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Supermercado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idsupermercado;
	
	@Column(name="razao_social")
	private String razaoSocial;
	
	@Column
	private String nome;
	
	@Column
	private String endereco;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Produto.class,fetch = FetchType.LAZY, mappedBy = "supermercado")
	private List<Produto> produtos;

	public Long getIdsupermercado() {
		return idsupermercado;
	}

	public void setIdsupermercado(Long idsupermercado) {
		this.idsupermercado = idsupermercado;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}
