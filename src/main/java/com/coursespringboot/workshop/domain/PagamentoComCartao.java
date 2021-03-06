package com.coursespringboot.workshop.domain;

import javax.persistence.Entity;

import com.coursespringboot.workshop.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	private Integer numDeParcelas;

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numDeParcelas) {
		super(id, estadoPagamento, pedido);
		this.numDeParcelas = numDeParcelas;
	}
	
	
	
}
