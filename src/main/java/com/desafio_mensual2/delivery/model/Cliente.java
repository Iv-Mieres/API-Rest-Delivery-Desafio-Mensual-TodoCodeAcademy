package com.desafio_mensual2.delivery.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.desafio_mensual2.delivery.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "clientes")
public class Cliente implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idCliente;
	@NotNull(message = "El campo username no puede estar vácio")
	@Size(min = 4, message = "debe contener un minimo de 4 caracteres")
	private String username;
	@NotNull(message = "El campo password no puede estar vácio")
	@Size(min = 4, message = "debe contener un minimo de 4 caracteres")
	private String password;
	@NotNull(message = "El campo password no puede estar vácio")
	private String repeatPassword;
	@Enumerated(EnumType.STRING)
	private Role role;
	@NotNull(message = "El campo nombre no puede estar vácio")
	@Size(min = 4, message = "debe contener un minimo de 4 caracteres")
	private String nombre;
	@NotNull(message = "El campo apellido no puede estar vácio")
	@Size(min = 4, message = "debe contener un minimo de 4 caracteres")
	private String apellido;
	@NotNull(message = "El campo celular no puede estar vácio")
	@Size(min = 10, message = "debe contener un minimo de 10 caracteres")
	private String celular;
	private String eliminado;

	public Cliente() {
	}

	public Cliente(Long idCliente, String username, String password, String repeatPassword, Role role, String nombre,
			String apellido, String celular) {
		this.idCliente = idCliente;
		this.username = username;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.role = role;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.eliminado = "false";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(this.eliminado.equalsIgnoreCase("true")) {
			return false;
		}
		return true;
	}

}
