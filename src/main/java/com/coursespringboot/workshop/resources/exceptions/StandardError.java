package com.coursespringboot.workshop.resources.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timeStamp;
	
	
}
