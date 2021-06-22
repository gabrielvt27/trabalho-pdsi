package com.pdsi.laissez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdsi.laissez.models.CarrinhoProduto;
import com.pdsi.laissez.repositories.CarrinhoProdutoRepository;

@Service
public class CarrinhoProdutoService {

	@Autowired
	CarrinhoProdutoRepository repository;
	
	public CarrinhoProduto create(CarrinhoProduto carrinhoProduto) {
		return repository.save(carrinhoProduto);
	}
	
	public void delete(CarrinhoProduto carrinhoProduto) {
		repository.delete(carrinhoProduto);
	}
	
	public CarrinhoProduto findByProdutoCarrinho(Long idcarrinho, Long idproduto) {
		return repository.findByProdutoCarrinho(idcarrinho, idproduto);
	}
	
	public List<CarrinhoProduto> findByIdCarrinho(Long idcarrinho) {
		return repository.findByIdCarrinho(idcarrinho);
	}
}
