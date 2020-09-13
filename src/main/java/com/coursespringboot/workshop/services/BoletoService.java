package com.coursespringboot.workshop.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.coursespringboot.workshop.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	public void preencherPagamentoComBoleto (PagamentoComBoleto pagamentoComBoleto, Date instanteDoPedido) {
		Calendar can = Calendar.getInstance();
		can.setTime(instanteDoPedido);
		can.add(Calendar.DAY_OF_WEEK, 7);
		pagamentoComBoleto.setDataVencimento(can.getTime());
	}
}
