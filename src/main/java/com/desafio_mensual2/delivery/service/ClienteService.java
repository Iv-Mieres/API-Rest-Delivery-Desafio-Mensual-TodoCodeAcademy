package com.desafio_mensual2.delivery.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio_mensual2.delivery.enums.Role;
import com.desafio_mensual2.delivery.model.Cliente;
import com.desafio_mensual2.delivery.model.exceptions.BadRequestException;
import com.desafio_mensual2.delivery.repository.IClienteRepository;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// CREAR ROLE ADMIN

	@Override
	public void saveAdmin() {
		Cliente admin = new Cliente(1L, "admin", "admin", "admin", Role.ADMIN, "administrador", "admin",
				"1154778287747");
		admin.setPassword(passwordEncoder.encode("admin"));
		clienteRepository.save(admin);
	}

	// CREAR CLIENTE

	@Override
	public Cliente saveCliente(Cliente cliente) {

		if (clienteRepository.existsByUsername(cliente.getUsername())) {
			throw new BadRequestException(
					"El cliente con username " + cliente.getUsername() + " ya existe. Ingrese un nuevo username");
		}
		if (!cliente.getPassword().equals(cliente.getRepeatPassword())) {
			throw new BadRequestException("El campo password debe ser igual a repeatPassword");
		}

		var setCliente = cliente;
		setCliente.setRepeatPassword(" ");
		setCliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
		setCliente.setRole(Role.CLIENTE);
		setCliente.setEliminado("false");

		return clienteRepository.save(setCliente);
	}

	// VER CLIENTE

	@Override
	public Cliente getCliente(HttpSession session) {
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");
		var clienteBD = clienteRepository.findById(clienteSession.getIdCliente()).orElse(null);

		return clienteBD;
	}

	// EDITAR CLIENTE

	@Override
	public void editCliente(HttpSession session, Cliente cliente) {
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");
		var clienteBD = clienteRepository.findByusername(clienteSession.getUsername());

		if (!clienteBD.getUsername().equals(cliente.getUsername())
				&& clienteRepository.existsByUsername(cliente.getUsername())) {
			throw new BadRequestException(
					"El cliente con username " + cliente.getUsername() + " ya existe. Ingrese un nuevo username");
		}
		if (!cliente.getPassword().equals(cliente.getRepeatPassword())) {
			throw new BadRequestException("El campo password debe ser igual a repeatPassword");
		}

		clienteSession = cliente;
		clienteSession.setPassword(passwordEncoder.encode(cliente.getPassword()));
		clienteSession.setRepeatPassword(" ");
		clienteSession.setRole(Role.CLIENTE);
		clienteSession.setEliminado("false");
		clienteSession.setIdCliente(clienteBD.getIdCliente());

		clienteRepository.save(clienteSession);

	}

	// ElIMINADO LÓGICO DE CLIENTE

	@Override
	public void deletCliente(HttpSession session) {
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");
		clienteSession.setEliminado("true");

		clienteRepository.save(clienteSession);

	}

}
