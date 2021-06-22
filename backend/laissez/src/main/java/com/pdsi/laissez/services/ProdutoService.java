package com.pdsi.laissez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdsi.laissez.models.Produto;
import com.pdsi.laissez.repositories.ProdutoRepository;


@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repository;

	public Produto create(Produto produto) {
		return repository.save(produto);
	}
	
	public List<Produto> listallproducts(){
		return repository.findAll();
	}
	
	public List<Produto> searchproducts(String produto){
		return repository.findByNomeContaining(produto);
	}
	
	public List<Produto> searchproducts2(String produto){
		return repository.findByNomeouTipo(produto);
	}
	
	public Produto findById(Long idproduto) {
		return repository.findById(idproduto).orElse(null);
	}
}
