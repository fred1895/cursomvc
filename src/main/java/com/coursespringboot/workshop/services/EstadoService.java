package com.coursespringboot.workshop.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.Estado;
import com.coursespringboot.workshop.dto.EstadoDTO;
import com.coursespringboot.workshop.repositories.EstadoRepository;
import com.coursespringboot.workshop.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public Estado findById(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}
	
	public EstadoDTO toDTO(Estado obj) {
		return new EstadoDTO(obj);
	}
	
	public List<EstadoDTO> objsToDTO(List<Estado> objs) {
		return objs.stream().map(EstadoDTO::new).collect(Collectors.toList());
	}
}
