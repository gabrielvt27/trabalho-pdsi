package com.pdsi.laissez.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pdsi.laissez.models.Produto;
import com.pdsi.laissez.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query(value="select * from usuario where endereco_email = ?1 and senha = ?2", nativeQuery = true)
	List<Usuario> findByEmailouSenha(String email, String senha);
}
