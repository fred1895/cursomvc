package com.coursespringboot.workshop.dto;

import com.coursespringboot.workshop.domain.Estado;

import lombok.Data;

@Data
public class EstadoDTO {
	private Integer id;
	private String nome;
	
	public EstadoDTO (Estado obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
}
