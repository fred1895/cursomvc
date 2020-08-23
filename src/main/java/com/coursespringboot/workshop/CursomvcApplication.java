package com.coursespringboot.workshop;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursespringboot.workshop.domain.Categoria;
import com.coursespringboot.workshop.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomvcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
	}


}
