package com.pdsi.laissez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdsi.laissez.models.Carrinho;
import com.pdsi.laissez.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	CarrinhoRepository repository;
	
	public Carrinho findById(Long idcarrinho) {
		return repository.findById(idcarrinho).orElse(null);
	}
	
	public Carrinho create(Carrinho carrinho) {
		return repository.save(carrinho);
	}
}