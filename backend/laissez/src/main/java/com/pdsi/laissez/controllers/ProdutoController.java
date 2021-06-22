package com.pdsi.laissez.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdsi.laissez.models.Produto;
import com.pdsi.laissez.models.Supermercado;
import com.pdsi.laissez.services.ProdutoService;
import com.pdsi.laissez.services.SupermercadoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService serviceProduto;
	
	@Autowired
	SupermercadoService serviceSupermercado;
	
	@GetMapping
	public List<Produto> listaProdutos() {
		return serviceProduto.listallproducts();
	}
	
	@GetMapping("/search/{nome}")
	public List<Produto> buscarPorNome(@PathVariable("nome") String nome) {
		return serviceProduto.searchproducts2(nome);
	}
	
	@PostMapping("/novo/{idsupermercado}")
	public ResponseEntity<?> createPessoa(@RequestBody Produto p, @PathVariable("idsupermercado") Long idsupermercado) {
		Supermercado s = serviceSupermercado.findById(idsupermercado);
		p.setSupermercado(s);
		if(s == null) {
			return new ResponseEntity<String>("{\"erro\":\"Supermercado não encontrada\"}",HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Produto>(serviceProduto.create(p),HttpStatus.CREATED);
		}
	}
}
