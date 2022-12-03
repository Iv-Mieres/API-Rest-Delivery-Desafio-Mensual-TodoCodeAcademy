package com.desafio_mensual2.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio_mensual2.delivery.model.Comida;

@Repository
public interface IComidaRepository extends JpaRepository<Comida, Long>{

	public boolean existsByNombreComida(String nombreComida);
}
