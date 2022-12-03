package com.desafio_mensual2.delivery.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idPedido;
	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente unCliente;
	
	@ManyToMany
	@JoinTable(name = "pedido_comidas",
	joinColumns = @JoinColumn(name = "idPedido"),
	inverseJoinColumns = @JoinColumn(name = "idComida"))
	@NotEmpty(message = "No puede estar vacia")
	@NotNull(message = "No puede estar vacia")
	private List<Comida> listaComidas;	

}
