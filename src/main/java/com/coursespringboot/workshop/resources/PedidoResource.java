package com.coursespringboot.workshop.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coursespringboot.workshop.domain.Pedido;
import com.coursespringboot.workshop.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	/*
	@RequestMapping(method = RequestMethod.GET)
	public List<Pedido> listar() {
		Pedido cat1 = new Pedido(1, "Fred");
		Pedido cat2 = new Pedido(2, "Camila");
		
		List<Pedido> list = new ArrayList<>();
		
		list.add(cat1);
		list.add(cat2);
		return list;
	}
	*/
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
