package com.coursespringboot.workshop.services;

//@Service
public class AuthService {
	
	/*
	@Autowired
	private ClientRepository clientRepository; 
	
	@Autowired
	private BCryptPasswordEncoder be;
	
	@Autowired
	private MailService mailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Client client = clientRepository.findByEmail(email);
		if(client == null) throw new ObjetoNaoEncontradoException("Cliente não encontrado");
		
		String newPass = newPassword();
		client.setSenha(be.encode(newPass));
		clientRepository.save(client);
		
		mailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {
		char[] vet =  new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) {//Gera numero
			return (char) (rand.nextInt(10 + 48));
		} else if (opt == 1) {
			return (char) (rand.nextInt(26 + 65));
		}  else {
			return (char) (rand.nextInt(26 + 97));
		}
	}
	*/
}
