package com.pdsi.laissez.produto.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.pdsi.laissez.controllers.UsuarioController;
import com.pdsi.laissez.models.Usuario;
import com.pdsi.laissez.services.CarrinhoProdutoService;
import com.pdsi.laissez.services.CarrinhoService;
import com.pdsi.laissez.services.ProdutoService;
import com.pdsi.laissez.services.SupermercadoService;
import com.pdsi.laissez.services.UsuarioService;

import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

@WebMvcTest
public class UsuarioControllerTest {

	@Autowired
	private UsuarioController uc;
	
	@MockBean
	private UsuarioService serviceUsuario;
	
	@MockBean
	private CarrinhoService serviceCarrinho;
	
	@MockBean
	private ProdutoService serviceProduto;
	
	@MockBean
	private SupermercadoService serviceSupermercado;
	
	@MockBean
	private CarrinhoProdutoService serviceCarrinhoProduto;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.uc);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoLogar() {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		Usuario u = new Usuario();
		u.setEnderecoemail("gabrieltiburcio@ufu.br");
		u.setSenha("1234");
		
		usuarios.add(u);
		
		Mockito.when(this.serviceUsuario.login("gabrieltiburcio@ufu.br", "1234")).thenReturn(usuarios);
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("enderecoemail", "gabrieltiburcio@ufu.br");
		requestParams.put("senha", "1234");
		
		given()
			.body(requestParams.toJSONString())
			.contentType(ContentType.JSON)
		.when()
			.post("/usuario/login")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarSucesso_QuandoLogarFalhar() {
		
		Mockito.when(this.serviceUsuario.login("gabrieltiburcio@ufu.br", "1234")).thenReturn(null);
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("enderecoemail", "gabrieltiburcio@ufu.br");
		requestParams.put("senha", "412341234123");
		
		given()
			.body(requestParams.toJSONString())
			.contentType(ContentType.JSON)
		.when()
			.post("/usuario/login")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
}
