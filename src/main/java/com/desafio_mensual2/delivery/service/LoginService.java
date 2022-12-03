package com.desafio_mensual2.delivery.service;

import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.desafio_mensual2.delivery.model.Cliente;
import com.desafio_mensual2.delivery.repository.IClienteRepository;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Cliente cliente = clienteRepository.findByusername(username);
		var permisos = new ArrayList<GrantedAuthority>();

		if (Objects.isNull(cliente) || !cliente.getUsername().equals(username)) {
			
			throw new UsernameNotFoundException("ACCESO DENEGADO: revise los datos ingresados y vuelva a intentarlo.");		
		}
		var p = new SimpleGrantedAuthority("ROLE_" + cliente.getRole().toString());
		permisos.add(p);
		
		// Devuelve los datos de la session iniciada
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("usersession", cliente);
		
		return new User(cliente.getUsername(), cliente.getPassword(), cliente.isEnabled(), true, true, true,
				permisos);
	}

}
