package com.desafio_mensual2.delivery.model.exceptions;

public class BadRequestException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public BadRequestException(String message) {
		super(message);
		
	}
}
