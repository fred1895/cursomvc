package com.coursespringboot.workshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coursespringboot.workshop.domain.Client;
import com.coursespringboot.workshop.services.ClientService;

@RestController
@RequestMapping(value = "/clientes")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	/*
	@RequestMapping(method = RequestMethod.GET)
	public List<Client> listar() {
		Client cat1 = new Client(1, "Fred");
		Client cat2 = new Client(2, "Camila");
		
		List<Client> list = new ArrayList<>();
		
		list.add(cat1);
		list.add(cat2);
		return list;
	}
	*/
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Client obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
