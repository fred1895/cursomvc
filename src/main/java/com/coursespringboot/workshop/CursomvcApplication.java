package com.coursespringboot.workshop;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursespringboot.workshop.domain.Categoria;
import com.coursespringboot.workshop.domain.Cidade;
import com.coursespringboot.workshop.domain.Estado;
import com.coursespringboot.workshop.domain.Produto;
import com.coursespringboot.workshop.repositories.CategoriaRepository;
import com.coursespringboot.workshop.repositories.CidadeRepository;
import com.coursespringboot.workshop.repositories.EstadoRepository;
import com.coursespringboot.workshop.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomvcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Celular", 501.40);
		Produto p2 = new Produto(null, "Mesa", 300.00);
		Produto p3 = new Produto(null, "Notebook", 1200.90);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2, cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado e1 = new Estado(null, "Rio de Janeiro");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Cabo Frio", e1);
		Cidade c2 = new Cidade(null, "Uberaba", e2);
		Cidade c3 = new Cidade(null, "Buzios", e1);
		Cidade c4 = new Cidade(null, "Guarulhos", e2);
		
		e1.getCidades().addAll(Arrays.asList(c1, c3));
		e2.getCidades().addAll(Arrays.asList(c2, c4));
		
		estadoRepo.saveAll(Arrays.asList(e1, e2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3, c4));
	}


}
