package com.coursespringboot.workshop.services;

import org.springframework.mail.SimpleMailMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockMailService extends AbstractMailService {

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		log.info("Enviando Email ....");
		log.info(msg.toString());
		log.info("Email enviado ");
	}

}
