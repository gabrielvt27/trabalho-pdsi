package com.pdsi.laissez.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdsi.laissez.models.Carrinho;
import com.pdsi.laissez.models.Usuario;
import com.pdsi.laissez.services.CarrinhoService;
import com.pdsi.laissez.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService serviceUsuario;
	
	@Autowired
	CarrinhoService serviceCarrinho;
	
	@PostMapping("/novo")
	public ResponseEntity<?> createUsuario(@RequestBody Usuario u){
		Usuario user = serviceUsuario.create(u);
		Carrinho cart = new Carrinho();
		
		cart.setUsuario(user);
		user.setCarrinho(cart);
		
		serviceUsuario.create(user);
		
		return new ResponseEntity<Usuario>(user,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUsuario(@RequestBody Usuario u){
		List<Usuario> user = serviceUsuario.login(u.getEnderecoemail(), u.getSenha());
		
		if(user.isEmpty()) {
			return new ResponseEntity<String>("{\"erro\":\"Credenciais Inválidas\"}",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Usuario>>(user,HttpStatus.OK);
	}
}
