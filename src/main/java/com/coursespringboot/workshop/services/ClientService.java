package com.coursespringboot.workshop.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import com.coursespringboot.workshop.services.exceptions.AuthorizationException;
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
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer imgSize;
	
	public Client findById(Integer id) {
		UserSS userSS = UserService.authenticated();
		
		if (userSS == null || !userSS.hasRole(Perfil.ADMIN) && !id.equals(userSS.getId())) {
			throw new AuthorizationException("Acesso negado");
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
	
	public Client findByEmail(String email) {
			UserSS userSS = UserService.authenticated();
			if (userSS == null || !userSS.hasRole(Perfil.ADMIN) && !email.equals(userSS.getUsername())) {
				throw new AuthorizationException("Acesso negado");
			}
			
			Client cli = repo.findByEmail(email);
			if (cli == null) {
				throw new ObjetoNaoEncontradoException("Cliente não encontrado");
			}
			return cli;
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
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS userSS = UserService.authenticated();
		
		if (userSS == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage bufferedImage = imageService.getJpgImageFromFile(multipartFile);
		bufferedImage = imageService.cropSquare(bufferedImage);
		bufferedImage = imageService.resize(bufferedImage, imgSize);
		
		String filename = String.format("%s%s%s", prefix, userSS.getId(), ".jpg");
		
		return s3Service.uploadFileAWS(imageService.getInputStream(bufferedImage, "jpg"), filename, "image");
		
	}
}
