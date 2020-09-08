package com.coursespringboot.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.Client;
import com.coursespringboot.workshop.dto.ClientDTO;
import com.coursespringboot.workshop.repositories.ClientRepository;
import com.coursespringboot.workshop.services.exceptions.DataIntegrityJpaException;
import com.coursespringboot.workshop.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	public Client findById(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateDate(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateDate(Client newObj, Client obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityJpaException("Erro de integridate. Não pode excluir clientes com associações. ");
		}
	}

	public List<Client> findAll() {
		return repo.findAll();
		
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
}
