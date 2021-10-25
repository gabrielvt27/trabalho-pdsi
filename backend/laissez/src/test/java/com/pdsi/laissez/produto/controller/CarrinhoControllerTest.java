package com.pdsi.laissez.produto.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.pdsi.laissez.controllers.CarrinhoController;
import com.pdsi.laissez.models.Carrinho;
import com.pdsi.laissez.models.CarrinhoProduto;
import com.pdsi.laissez.models.Produto;
import com.pdsi.laissez.services.CarrinhoProdutoService;
import com.pdsi.laissez.services.CarrinhoService;
import com.pdsi.laissez.services.ProdutoService;
import com.pdsi.laissez.services.SupermercadoService;
import com.pdsi.laissez.services.UsuarioService;

import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@WebMvcTest
public class CarrinhoControllerTest {
	
	@Autowired
	private CarrinhoController cc;

	@MockBean
	private CarrinhoProdutoService cs;
	
	
	@MockBean
	private CarrinhoService serviceCarrinho;
	
	@MockBean
	private ProdutoService serviceProduto;
	
	@MockBean
	private SupermercadoService serviceSupermercado;
	
	@MockBean
	private UsuarioService serviceUsuario;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.cc);
	}
	
	 
	@Test
	public void deveRetornarSucesso_search() {

		Carrinho ca = new Carrinho();
		Carrinho ca1 = new Carrinho();
		Produto p = new Produto();
		CarrinhoProduto c = new CarrinhoProduto();
		
		List<CarrinhoProduto> carrinhos = new ArrayList<CarrinhoProduto>();
		
		ca.setId(1L);
		
		p.setIdproduto(1L);
		p.setPreco(10.0);
		
		c.setCarrinho(ca);
		c.setProduto(p);
		c.setQuantidadeproduto(1);
		c.setRegisteredAt(null);
		
		carrinhos.add(c);
		
		Mockito.when(this.cs.findByIdCarrinho(1L)).thenReturn(carrinhos);
		Mockito.when(this.serviceCarrinho.findById(1L)).thenReturn(ca1);
			
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/carrinho/search/{idcarrinho}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_search() {
		
		Mockito.when(this.cs.findByIdCarrinho(0L)).thenReturn(null);
		Mockito.when(this.serviceCarrinho.findById(0L)).thenReturn(null);
			
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/carrinho/search/{idcarrinho}", 0L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}

