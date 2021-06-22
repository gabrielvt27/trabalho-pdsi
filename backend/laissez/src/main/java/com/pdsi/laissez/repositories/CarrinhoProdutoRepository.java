package com.pdsi.laissez.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pdsi.laissez.models.CarrinhoProduto;

public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProduto, Long>{
	@Query(value="select * from carrinho_produto c where c.carrinho_id = ?1 and c.produto_id = ?2", nativeQuery = true)
	CarrinhoProduto findByProdutoCarrinho(Long idcarrinho, Long idproduto);
	
	@Query(value="select * from carrinho_produto c where c.carrinho_id = ?1", nativeQuery = true)
	List<CarrinhoProduto> findByIdCarrinho(Long idcarrinho);
}
