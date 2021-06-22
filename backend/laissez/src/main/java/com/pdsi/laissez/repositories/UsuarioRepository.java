package com.pdsi.laissez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdsi.laissez.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
