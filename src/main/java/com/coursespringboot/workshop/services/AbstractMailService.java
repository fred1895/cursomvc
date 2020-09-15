package com.coursespringboot.workshop.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.coursespringboot.workshop.domain.Pedido;

public abstract class AbstractMailService implements MailService {
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessage(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessage(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(sender);
		sm.setFrom(obj.getClient().getEmail());
		sm.setSubject("Pedido confirmado. Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
