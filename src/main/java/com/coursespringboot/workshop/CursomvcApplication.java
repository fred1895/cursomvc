package com.coursespringboot.workshop;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursespringboot.workshop.domain.Categoria;
import com.coursespringboot.workshop.domain.Cidade;
import com.coursespringboot.workshop.domain.Client;
import com.coursespringboot.workshop.domain.Endereco;
import com.coursespringboot.workshop.domain.Estado;
import com.coursespringboot.workshop.domain.ItemPedido;
import com.coursespringboot.workshop.domain.Pagamento;
import com.coursespringboot.workshop.domain.PagamentoComBoleto;
import com.coursespringboot.workshop.domain.PagamentoComCartao;
import com.coursespringboot.workshop.domain.Pedido;
import com.coursespringboot.workshop.domain.Produto;
import com.coursespringboot.workshop.domain.enums.EstadoPagamento;
import com.coursespringboot.workshop.domain.enums.TipoCliente;
import com.coursespringboot.workshop.repositories.CategoriaRepository;
import com.coursespringboot.workshop.repositories.CidadeRepository;
import com.coursespringboot.workshop.repositories.ClientRepository;
import com.coursespringboot.workshop.repositories.EnderecoRepository;
import com.coursespringboot.workshop.repositories.EstadoRepository;
import com.coursespringboot.workshop.repositories.ItemPedidoRepository;
import com.coursespringboot.workshop.repositories.PagamentoRepository;
import com.coursespringboot.workshop.repositories.PedidoRepository;
import com.coursespringboot.workshop.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomvcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository  produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursomvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Beleza");
		Categoria cat4 = new Categoria(null, "Computadores");
		Categoria cat5 = new Categoria(null, "Comida");
		Categoria cat6 = new Categoria(null, "Roupa");
		Categoria cat7 = new Categoria(null, "Plantas");
		
		Produto p1 = new Produto(null, "Celular", 501.40);
		Produto p2 = new Produto(null, "Mesa", 300.00);
		Produto p3 = new Produto(null, "Notebook", 1200.90);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2, cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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
		
		Client cli1 = new Client(null, "Fred", "fred@gmail.com", "4581254785", TipoCliente.PESSOAFISICA);
		Client cli2 = new Client(null, "Maria", "maria@gmail.com", "37278264", TipoCliente.PESSOAJURIDICA);
		
		cli1.getTelefones().addAll(Arrays.asList("988776655", "26209876"));
		cli2.getTelefones().addAll(Arrays.asList("988336655", "26339876"));
		
		Endereco end1 = new Endereco(null, "Rua das arraias", "26", "Casa 2", "Cajueiro", "28924248", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua das flores", "36", "Casa 1", "Cajo", "28666248", cli2, c4);
		Endereco end3 = new Endereco(null, "Rua das tainhas", "16", "Casa 3", "Pero", "28666278", cli1, c1);
		Endereco end4 = new Endereco(null, "Rua das olivias", "6", "Casa 5", "Pe", "28446278", cli2, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end3));
		
		clientRepo.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepo.saveAll(Arrays.asList(end1, end2, end3, end4));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("20/10/2019 14:00"), cli1, end3);
		Pedido ped2 = new Pedido(null, sdf.parse("10/12/2018 20:30"), cli2, end4);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/06/2020 00:00"), sdf.parse("20/12/2020 00:00"));
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1));
		cli2.getPedidos().addAll(Arrays.asList(ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}


}
