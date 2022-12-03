package com.desafio_mensual2.delivery.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio_mensual2.delivery.enums.Role;
import com.desafio_mensual2.delivery.model.Cliente;
import com.desafio_mensual2.delivery.model.Comida;
import com.desafio_mensual2.delivery.model.exceptions.BadRequestException;
import com.desafio_mensual2.delivery.repository.IComidaRepository;

@Service
public class ComidaService implements IComidaService {

	@Autowired
	private IComidaRepository comidaRepository;
	
	// CREAR COMIDA
	
	@Override
	public void saveComida(HttpSession session, Comida comida) {
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");
		
		if(comidaRepository.existsByNombreComida(comida.getNombreComida())) {
			throw new BadRequestException("El nombre de comida ingresado ya existe."
					+ " Ingrese un nuevo nombre de comida!");
		}
		if(clienteSession.getRole().equals(Role.ADMIN)) {
			 comidaRepository.save(comida);
		}	
	}
	
	//MOSTRAR LISTA DE COMIDAS
	
	@Override
	public List<Comida> getListaComidas(){
		return comidaRepository.findAll();
	}
	
	// EDITAR COMIDA
	
	@Override
	public void editComida(HttpSession session, Comida comida) {
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");
		Comida comidaBD = comidaRepository.findById(comida.getIdComida())
				.orElseThrow(() -> new BadRequestException("El id " + comida.getIdComida() + " no existe."
						+ " Ingrese un id válido"));
			
		var listaComidasBD = comidaRepository.findAll();
		
		for (Comida comida2 : listaComidasBD) {
			if(comida2.getNombreComida().equals(comida.getNombreComida()) &&
					!comida2.getIdComida().equals(comida.getIdComida())) {
				throw new BadRequestException("El nombre de comida ingresado ya existe."
						+ " Ingrese un nuevo nombre de comida!");
			}
		}	
		if(clienteSession.getRole().equals(Role.ADMIN)) {		
				comidaBD = comida;
		}	
		comidaRepository.save(comidaBD);
	}
	
	// ELIMINAR COMIDAS
	
	@Override
	public void deleteComida(HttpSession session, Long idComida) {
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");
		if(!comidaRepository.existsById(idComida)) {
			throw new BadRequestException("El id " + idComida + " no existe. Ingrese un id válido"); 
		}
		if(clienteSession.getRole().equals(Role.ADMIN)) {
			comidaRepository.deleteById(idComida);
			
		}	
			
	}

}
