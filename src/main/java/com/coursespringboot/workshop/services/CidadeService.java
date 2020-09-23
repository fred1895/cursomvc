package com.coursespringboot.workshop.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.Cidade;
import com.coursespringboot.workshop.dto.CidadeDTO;
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
	
	public List<Cidade> findAll() {
		return repo.findAll();
	}
	
	public CidadeDTO toDTO(Cidade obj) {
		return new CidadeDTO(obj);
	}
	
	public List<CidadeDTO> objsToDTO(List<Cidade> objs) {
		return objs.stream().map(CidadeDTO::new).collect(Collectors.toList());
	}
}
