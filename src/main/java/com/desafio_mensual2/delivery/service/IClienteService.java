package com.desafio_mensual2.delivery.service;

import javax.servlet.http.HttpSession;

import com.desafio_mensual2.delivery.model.Cliente;

public interface IClienteService {

	public Cliente saveCliente(Cliente cliente);

	public Cliente getCliente(HttpSession session);

	public void editCliente(HttpSession session, Cliente cliente);

	public void deletCliente(HttpSession session);

	public void saveAdmin();
}
