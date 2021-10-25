package com.pdsi.laissez.produto.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.mockito.*;

import com.pdsi.laissez.controllers.CarrinhoController;
import com.pdsi.laissez.controllers.ProdutoController;
import com.pdsi.laissez.controllers.UsuarioController;
import com.pdsi.laissez.models.Produto;
import com.pdsi.laissez.services.CarrinhoService;
import com.pdsi.laissez.services.ProdutoService;
import com.pdsi.laissez.services.SupermercadoService;

import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class ProdutoControllerTest {
	
	@Autowired
	private ProdutoController produtoController;
	
	@MockBean
	private CarrinhoController carrinhoCrontroller;
	
	@MockBean
	private UsuarioController usuarioCrontroller;
	
	@MockBean
	private ProdutoService serviceProduto;
	
	@MockBean
	private SupermercadoService serviceSupermercado;
	
	
	
	@MockBean
	private CarrinhoService serviceCarrinho;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.produtoController);
	}
	
	@Test
	public void deveRetornarEncontrado_QuandoBuscarProduto() {
		List<Produto> produtos = new ArrayList<Produto>();
		Produto p1 = new Produto();
		p1.setDescricao("Del Valle uva");
		Produto p2 = new Produto();
		p2.setDescricao("Del Valle maracujá");
		
		produtos.add(p1);
		produtos.add(p2);
		
		Mockito.when(this.serviceProduto.searchproducts2("Del"))
			.thenReturn(produtos);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/produto/search/{nome}", "Del")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarProduto() {
		
		Mockito.when(this.serviceProduto.searchproducts2(""))
			.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/produto/search/{nome}", "")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
}
