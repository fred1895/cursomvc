package com.coursespringboot.workshop.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.coursespringboot.workshop.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
	
}
