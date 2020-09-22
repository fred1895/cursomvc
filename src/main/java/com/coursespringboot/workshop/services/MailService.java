package com.coursespringboot.workshop.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.Client;
import com.coursespringboot.workshop.domain.Pedido;

@Service
public interface MailService {
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Client client, String newPass);
}
