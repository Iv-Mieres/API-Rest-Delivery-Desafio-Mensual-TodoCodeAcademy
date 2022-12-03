package com.desafio_mensual2.delivery.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_mensual2.delivery.model.Cliente;
import com.desafio_mensual2.delivery.service.IClienteService;

@RestController
@RequestMapping("/usuarios")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@PostMapping("/crear")
	public ResponseEntity<Cliente> crearUsuarioCliente(@RequestBody @Valid Cliente cliente){
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(cliente));
		
	}
	
	@PostMapping("/crear_admin")
	public ResponseEntity<String> crearAdmin(){
		clienteService.saveAdmin();
		return ResponseEntity.status(HttpStatus.CREATED).body("El role admin ha sido creado");
		
	}
	
	@PreAuthorize("hasRole('CLIENTE')")
	@GetMapping("/mi_perfil")
	public ResponseEntity<Cliente> getCliente(HttpSession session){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.getCliente(session));
	}
	
	@PreAuthorize("hasRole('CLIENTE')")
	@PostMapping("mi_perfil/editar")
	public ResponseEntity<String> editCliente(HttpSession session, @RequestBody Cliente cliente){
		clienteService.editCliente(session, cliente);
		return ResponseEntity.status(HttpStatus.OK).body("Su perfil se ha editado correctamente!");
	}
	
	@PreAuthorize("hasRole('CLIENTE')")
	@DeleteMapping("mi_perfil/eliminar")
	public ResponseEntity<String> deleteCliente(HttpSession session){
		clienteService.deletCliente(session);
		return ResponseEntity.status(HttpStatus.OK).body("Su cuenta de usuario ha sido eliminada");
	}
	

}
