package com.coursespringboot.workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.Cidade;
import com.coursespringboot.workshop.repositories.CidadeRepository;
import com.coursespringboot.workshop.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public Cidade findById(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
}
