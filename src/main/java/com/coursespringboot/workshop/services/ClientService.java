package com.coursespringboot.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.Cidade;
import com.coursespringboot.workshop.domain.Client;
import com.coursespringboot.workshop.domain.Endereco;
import com.coursespringboot.workshop.domain.enums.Perfil;
import com.coursespringboot.workshop.domain.enums.TipoCliente;
import com.coursespringboot.workshop.dto.ClientDTO;
import com.coursespringboot.workshop.dto.ClientNewDTO;
import com.coursespringboot.workshop.repositories.ClientRepository;
import com.coursespringboot.workshop.repositories.EnderecoRepository;
import com.coursespringboot.workshop.security.UserSS;
import com.coursespringboot.workshop.services.exceptions.AuthorzationException;
import com.coursespringboot.workshop.services.exceptions.DataIntegrityJpaException;
import com.coursespringboot.workshop.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class ClientService {
	
	@Autowired
	BCryptPasswordEncoder be;
	
	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Client findById(Integer id) {
		UserSS userSS = UserService.authenticated();
		
		if (userSS == null || !userSS.hasRole(Perfil.ADMIN) && !id.equals(userSS.getId())) {
			throw new AuthorzationException("Acesso negado");
		}
		
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));
	}
	
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
		return new Client(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), be.encode(objDTO.getSenha()));
		Cidade cidade = new Cidade(objDTO.getCidade(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getBairro(), cli, cidade);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}
}
