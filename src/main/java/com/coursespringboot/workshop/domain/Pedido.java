package com.coursespringboot.workshop.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date instant;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "enderereco_de_entrega_id")
	private Endereco enderecoDeEntrega;

	public Pedido(Integer id, Date instant, Client client, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instant = instant;
		this.client = client;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
	
}
