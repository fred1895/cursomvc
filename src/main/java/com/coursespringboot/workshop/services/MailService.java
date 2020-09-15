package com.coursespringboot.workshop.services;

import org.springframework.mail.SimpleMailMessage;

import com.coursespringboot.workshop.domain.Pedido;

public interface MailService {
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
