package com.pdsi.laissez.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pdsi.laissez.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByNomeContaining(String produto);
	
	@Query(value="select * from produto where nome like %?1% or tipo like %?1% or marca like %?1% or descricao like %?1%", nativeQuery = true)
	List<Produto> findByNomeouTipo(String produto);
}
