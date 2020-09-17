package com.coursespringboot.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.Client;
import com.coursespringboot.workshop.repositories.ClientRepository;
import com.coursespringboot.workshop.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = repo.findByEmail(email);
		if(client == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(client.getId(), client.getEmail(), client.getSenha(), client.getPerfis());
	}

}
