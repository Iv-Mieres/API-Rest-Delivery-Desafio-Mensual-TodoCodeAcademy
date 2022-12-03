package com.desafio_mensual2.delivery.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio_mensual2.delivery.enums.Role;
import com.desafio_mensual2.delivery.model.Cliente;
import com.desafio_mensual2.delivery.model.Comida;
import com.desafio_mensual2.delivery.model.Pedido;
import com.desafio_mensual2.delivery.model.exceptions.BadRequestException;
import com.desafio_mensual2.delivery.repository.IComidaRepository;
import com.desafio_mensual2.delivery.repository.IPedidoRepository;

@Service
public class PedidoService implements IPedidoService {

	@Autowired
	private IPedidoRepository pedidoRepository;

	@Autowired
	private IComidaRepository comidaRepository;

	// CREAR PEDIDO

	@Override
	public Pedido savePedido(HttpSession session, Pedido pedido) {
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");

		for (Comida comida : pedido.getListaComidas()) {
			if (!comidaRepository.existsById(comida.getIdComida())) {
				throw new BadRequestException("el id " + comida.getIdComida() + " de la comida seleccionada no existe"
						+ " Ingrese un id válido!");
			}
		}

		Pedido guardarPedido = pedido;
		guardarPedido.setUnCliente(clienteSession);

		return pedidoRepository.save(guardarPedido);
	}

	// MOSTRAR PEDIDO POR ID

	@Override
	public Pedido getPedido(HttpSession session, Long idPedido) {
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");
		Pedido pedidoBD = pedidoRepository.findById(idPedido).orElseThrow(
				() -> new BadRequestException("El id " + idPedido + " es incorrecto. Ingrese un id válido"));

		if (clienteSession.getRole().equals(Role.ADMIN)) {
			return pedidoBD;
		}
		if (!clienteSession.getUsername().equals(pedidoBD.getUnCliente().getUsername())) {
			throw new BadRequestException("El id " + idPedido + " es incorrecto. Ingrese un id válido");
		}

		return pedidoBD;
	}

	// MOSTRAR LISTA DE PEDIDOS

	@Override
	public List<Pedido> listaPedidos() {
		var listaPedidos = pedidoRepository.findAll();
		var guardarPedidos = new ArrayList<Pedido>();

		for (Pedido pedido : listaPedidos) {
			if (pedido.getUnCliente().isEnabled()) {
				guardarPedidos.add(pedido);
			}
		}
		return guardarPedidos;
	}

	// ELIMINAR PEDIDO

	@Override
	public void deletePedido(HttpSession session, Long idPedido){
		Cliente clienteSession = (Cliente) session.getAttribute("usersession");
		Pedido pedidoBD = pedidoRepository.findById(idPedido).orElseThrow(
				() -> new BadRequestException("El id de pedido " + idPedido + " es incorrecto. Ingrese un id válido"));

		if (!pedidoBD.getUnCliente().getUsername().equals(clienteSession.getUsername())) {
			throw new BadRequestException("El id de pedido " + idPedido + " es incorrecto. Ingrese un id válido");
		}
		pedidoRepository.deleteById(idPedido);

	}

}
