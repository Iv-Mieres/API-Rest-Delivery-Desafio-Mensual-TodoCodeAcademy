package com.desafio_mensual2.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio_mensual2.delivery.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByusername(String username);
	boolean existsByUsername(String username);

}
