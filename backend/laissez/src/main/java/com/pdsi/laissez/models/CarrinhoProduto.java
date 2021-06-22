package com.pdsi.laissez.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CarrinhoProduto {

	public CarrinhoProduto(){
	}
	public CarrinhoProduto(Carrinho carrinho, Produto produto, int quantidadeproduto,
			LocalDateTime registeredAt) {
		super();
		this.carrinho = carrinho;
		this.produto = produto;
		this.quantidadeproduto = quantidadeproduto;
		this.registeredAt = registeredAt;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcarrinhoproduto;
	
	@ManyToOne
	@JoinColumn(name = "carrinho_id")
	Carrinho carrinho;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	Produto produto;
	
	private int quantidadeproduto;
	
	private LocalDateTime registeredAt;

	public Long getIdcarrinhoproduto() {
		return idcarrinhoproduto;
	}

	public void setIdcarrinhoproduto(Long idcarrinhoproduto) {
		this.idcarrinhoproduto = idcarrinhoproduto;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidadeproduto() {
		return quantidadeproduto;
	}

	public void setQuantidadeproduto(int quantidadeproduto) {
		this.quantidadeproduto = quantidadeproduto;
	}

	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}
	
	
}
