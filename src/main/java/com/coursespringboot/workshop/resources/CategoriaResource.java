package com.coursespringboot.workshop.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coursespringboot.workshop.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1, "Fred");
		Categoria cat2 = new Categoria(2, "Camila");
		
		List<Categoria> list = new ArrayList<>();
		
		list.add(cat1);
		list.add(cat2);
		return list;
	}
}
