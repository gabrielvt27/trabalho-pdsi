package com.pdsi.laissez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdsi.laissez.models.Supermercado;
import com.pdsi.laissez.repositories.SupermercadoRepository;

@Service
public class SupermercadoService {

	@Autowired
	SupermercadoRepository repository;
	
	public Supermercado findById(Long idsupermercado) {
		return repository.findById(idsupermercado).orElse(null);
	}
}
