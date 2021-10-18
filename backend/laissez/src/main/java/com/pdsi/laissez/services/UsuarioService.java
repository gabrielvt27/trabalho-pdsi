package com.pdsi.laissez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdsi.laissez.models.Produto;
import com.pdsi.laissez.models.Usuario;
import com.pdsi.laissez.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	public Usuario create(Usuario pessoa) {
		return repository.save(pessoa);
	}
	
	public List<Usuario> login(String email, String senha){
		return repository.findByEmailouSenha(email, senha);
	}
}
