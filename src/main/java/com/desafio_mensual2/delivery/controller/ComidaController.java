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

import com.desafio_mensual2.delivery.model.Comida;
import com.desafio_mensual2.delivery.service.IComidaService;

@RestController
public class ComidaController {
	
	@Autowired
	private IComidaService comidaService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/crear_comida")
	public ResponseEntity<String> crearComida(HttpSession session, @RequestBody @Valid Comida comida){
		comidaService.saveComida(session, comida);
		return ResponseEntity.status(HttpStatus.CREATED).body("La comida fue creada correctamente!");
	}
	
	@GetMapping("/usuarios/ver_menu")
	public ResponseEntity<List<Comida>> listaComidas(){
		return ResponseEntity.status(HttpStatus.OK).body(comidaService.getListaComidas());
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/editar_comida")
	public ResponseEntity<String> editarComida(HttpSession session, @RequestBody @Valid Comida comida){
		comidaService.editComida(session, comida);
		return ResponseEntity.status(HttpStatus.CREATED).body("La comida fue editada correctamente!");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/admin/eliminar_comida/{idComida}")
	public ResponseEntity<String> eliminarComida(HttpSession session, @PathVariable Long idComida){
		comidaService.deleteComida(session, idComida);
		return ResponseEntity.status(HttpStatus.CREATED).body("La comida fue eliminada correctamente!");
	}
	
	
	
	
	
	
	
}
