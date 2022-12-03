package com.desafio_mensual2.delivery.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.desafio_mensual2.delivery.model.Comida;

public interface IComidaService {

	public void saveComida(HttpSession session, Comida comida);

	void editComida(HttpSession session, Comida comida);

	List<Comida> getListaComidas();

	void deleteComida(HttpSession session, Long idComida);
}
