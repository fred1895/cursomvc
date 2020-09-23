package com.coursespringboot.workshop.dto;

import com.coursespringboot.workshop.domain.Cidade;

import lombok.Data;

@Data
public class CidadeDTO {
	private Integer id;
	private String nome;
	
	public CidadeDTO (Cidade obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
}
