package com.desafio_mensual2.delivery.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_mensual2.delivery.model.Pedido;
import com.desafio_mensual2.delivery.service.IPedidoService;

@RestController
public class PedidoController {

	@Autowired
	private IPedidoService pedidoService;
	
	
	@PreAuthorize("hasRole('CLIENTE')")
	@PostMapping("/pedidos/crear")
	public ResponseEntity<String> crearPedido(HttpSession session, @RequestBody @Valid Pedido pedido){
		pedidoService.savePedido(session, pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body("Su pedido fue realizado correctamente!");
	}
	
	@PreAuthorize("hasRole('CLIENTE') OR hasRole('ADMIN')")
	@GetMapping("/pedidos/mostrar/{idPedido}")
	public ResponseEntity<Pedido> mostrarPedido(HttpSession session,  @PathVariable Long idPedido){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.getPedido(session, idPedido));
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/pedidos/mostrar_pedidos")
	public ResponseEntity<List<Pedido>> listaPedidos(){
		return ResponseEntity.status(HttpStatus.OK).body(pedidoService.listaPedidos());
	}
	
	@PreAuthorize("hasRole('CLIENTE') OR hasRole('ADMIN')")
	@DeleteMapping("/pedidos/eliminar/{idPedido}")
	public ResponseEntity<String> eliminarPedido(HttpSession session, @PathVariable Long idPedido){
		pedidoService.deletePedido(session, idPedido);
		return ResponseEntity.status(HttpStatus.OK).body("El pedido con id " + idPedido + " ha sido eliminado");
	}
	
	
	
	
	
}
