package com.pdsi.laissez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdsi.laissez.models.Supermercado;

@Repository
public interface SupermercadoRepository extends JpaRepository<Supermercado, Long>{

}
