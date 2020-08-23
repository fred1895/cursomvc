package com.coursespringboot.workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.Produto;
import com.coursespringboot.workshop.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElse(null); 
	}
}
