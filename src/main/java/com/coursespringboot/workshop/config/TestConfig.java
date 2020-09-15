package com.coursespringboot.workshop.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.coursespringboot.workshop.services.DBService;
import com.coursespringboot.workshop.services.MailService;
import com.coursespringboot.workshop.services.MockMailService;

@Configuration
@Profile("teste")
public class TestConfig {
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		
		dbService.instantiateTestDataBase();
		return true;
	}
	
	@Bean
	public MailService mailService() {
		return new MockMailService();
	}
}
