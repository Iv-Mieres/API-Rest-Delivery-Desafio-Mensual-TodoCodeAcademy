package com.desafio_mensual2.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio_mensual2.delivery.model.Cliente;
import com.desafio_mensual2.delivery.model.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

	public Pedido findByUnCliente(Cliente unCliente);
}
