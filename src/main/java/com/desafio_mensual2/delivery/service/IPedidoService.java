package com.desafio_mensual2.delivery.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.desafio_mensual2.delivery.model.Pedido;


public interface IPedidoService {

	public Pedido savePedido(HttpSession session, Pedido pedido);

	public Pedido getPedido(HttpSession session, Long idPedido);

	public List<Pedido> listaPedidos();

	public void deletePedido(HttpSession session, Long idPedido);
}
