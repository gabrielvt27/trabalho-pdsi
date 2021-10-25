package com.pdsi.laissez.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pdsi.laissez.models.Carrinho;
import com.pdsi.laissez.models.CarrinhoProduto;
import com.pdsi.laissez.models.Produto;
import com.pdsi.laissez.services.CarrinhoProdutoService;
import com.pdsi.laissez.services.CarrinhoService;
import com.pdsi.laissez.services.ProdutoService;

@RestController
@RequestMapping(value="/carrinho",produces="application/json")
public class CarrinhoController {
	
	@Autowired
	CarrinhoService serviceCarrinho;
	
	@Autowired
	ProdutoService serviceProduto;
	
	@Autowired
	CarrinhoProdutoService serviceCarrinhoProduto;
	
	@GetMapping("/search/{idcarrinho}")
	public ResponseEntity<?> buscaProdutosCarrinho(@PathVariable("idcarrinho") Long idcarrinho) throws JsonProcessingException{
		List<CarrinhoProduto> lcp = serviceCarrinhoProduto.findByIdCarrinho(idcarrinho); // lista de produtos no carrinho
		Carrinho c = serviceCarrinho.findById(idcarrinho); // valor total carrinho
		
		if(c == null) {
			return ResponseEntity.notFound().build();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("total", c.getValor());
		
		ArrayNode arrayNode = mapper.createArrayNode();

		for (CarrinhoProduto obj : lcp) {
			ObjectMapper m = new ObjectMapper();
		    ObjectNode cp = m.createObjectNode();

		    cp.put("idproduto", obj.getProduto().getIdproduto());
		    cp.put("quantidade", obj.getQuantidadeproduto());
		    cp.put("imagem", obj.getProduto().getImagem());
		    cp.put("preco", Math.round(obj.getProduto().getPreco() * obj.getQuantidadeproduto() * 100.0)/100.0);
		    cp.put("nome", obj.getProduto().getNome());
		    
		    arrayNode.add(cp);
		}
		
		rootNode.set("produtos", arrayNode);
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
		
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}

	@PutMapping("/add/{idcarrinho}/{idproduto}")
	public ResponseEntity<?> addCarrinho(@PathVariable("idcarrinho") Long idcarrinho, @PathVariable("idproduto") Long idproduto) {
		Carrinho c = serviceCarrinho.findById(idcarrinho);
		Produto p = serviceProduto.findById(idproduto);
		

		if(c == null || p == null) {
			return new ResponseEntity<String>("{\"erro\":\"Carrinho não encontrado\"}",HttpStatus.NOT_FOUND);
		}
		
		CarrinhoProduto scp = serviceCarrinhoProduto.findByProdutoCarrinho(idcarrinho, idproduto);
		LocalDateTime nowDate = LocalDateTime.now();
		c.setValor(c.getValor() + p.getPreco());
		if(scp == null){
			CarrinhoProduto cp = new CarrinhoProduto(c, p, 1, nowDate);
			serviceCarrinhoProduto.create(cp);
			serviceCarrinho.create(c);
			return new ResponseEntity<String>("{\"sucess\":\"Item adicionado no carrinho\"}",HttpStatus.OK);
		}else {
			scp.setQuantidadeproduto(scp.getQuantidadeproduto()+1);
			scp.setRegisteredAt(nowDate);
			serviceCarrinhoProduto.create(scp);
			serviceCarrinho.create(c);
			return new ResponseEntity<String>("{\"sucess\":\"Item adicionado no carrinho\"}",HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/remove/{idcarrinho}/{idproduto}")
	public ResponseEntity<?> removeOneCarrinho(@PathVariable("idcarrinho") Long idcarrinho, @PathVariable("idproduto") Long idproduto){
		Carrinho c = serviceCarrinho.findById(idcarrinho);
		Produto p = serviceProduto.findById(idproduto);
		
		if(c == null || p == null) {
			return new ResponseEntity<String>("{\"erro\":\"Carrinho não encontrado\"}",HttpStatus.NOT_FOUND);
		}
		
		CarrinhoProduto cp = serviceCarrinhoProduto.findByProdutoCarrinho(idcarrinho, idproduto);
		
		c.setValor(c.getValor() - (cp.getQuantidadeproduto() * p.getPreco()));
		serviceCarrinho.create(c);
		serviceCarrinhoProduto.delete(cp);
		
		return new ResponseEntity<String>("{\"sucess\":\"Item removido do carrinho\"}",HttpStatus.OK);
	}
	
	@DeleteMapping("/removeone/{idcarrinho}/{idproduto}")
	public ResponseEntity<?> removeCarrinho(@PathVariable("idcarrinho") Long idcarrinho, @PathVariable("idproduto") Long idproduto){
		Carrinho c = serviceCarrinho.findById(idcarrinho);
		Produto p = serviceProduto.findById(idproduto);
		

		if(c == null || p == null) {
			return new ResponseEntity<String>("{\"erro\":\"Carrinho não encontrado\"}",HttpStatus.NOT_FOUND);
		}
		
		CarrinhoProduto scp = serviceCarrinhoProduto.findByProdutoCarrinho(idcarrinho, idproduto);
		LocalDateTime nowDate = LocalDateTime.now();
		
		if(scp == null){
			return new ResponseEntity<String>("{\"erro\":\"Produto do carrinho não encontrado\"}",HttpStatus.NOT_FOUND);
		}else {

			c.setValor(c.getValor() - p.getPreco());
			serviceCarrinho.create(c);
			int qtdAtual = scp.getQuantidadeproduto() - 1;
			if(qtdAtual == 0) {
				serviceCarrinhoProduto.delete(scp);
				return new ResponseEntity<String>("{\"sucess\":\"Item removido do carrinho\"}",HttpStatus.OK);
			}else {
				scp.setQuantidadeproduto(qtdAtual);
				scp.setRegisteredAt(nowDate);
				serviceCarrinhoProduto.create(scp);
				return new ResponseEntity<String>("{\"sucess\":\"Item removido do carrinho\"}",HttpStatus.OK);
			}
		}
	}
	
	@DeleteMapping("/removeall/{idcarrinho}")
	public ResponseEntity<?> removeAllCarrinho(@PathVariable("idcarrinho") Long idcarrinho){
		Carrinho c = serviceCarrinho.findById(idcarrinho);
		
		if(c == null) {
			return new ResponseEntity<String>("{\"erro\":\"Carrinho não encontrado\"}",HttpStatus.NOT_FOUND);
		}
		
		c.setValor(0.00);
		serviceCarrinho.create(c);
		List<CarrinhoProduto> lcp = serviceCarrinhoProduto.findByIdCarrinho(idcarrinho);
		
		for (CarrinhoProduto cp : lcp) {
			serviceCarrinhoProduto.delete(cp);
		}
		
		return new ResponseEntity<String>("{\"sucess\":\"Compra finalizada\"}",HttpStatus.OK);
	}
}
