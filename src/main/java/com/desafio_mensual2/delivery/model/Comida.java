package com.desafio_mensual2.delivery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "comidas")
public class Comida {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idComida;
	@NotNull(message = "El campo bombreComida no puede estar vácio")
	@Size(min = 4, message = "debe contener un minimo de 4 caracteres")
	private String nombreComida;
	@NotNull(message = "El campo precio no puede estar vácio")
	private double precio;

}
