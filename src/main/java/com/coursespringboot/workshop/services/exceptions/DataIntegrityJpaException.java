package com.coursespringboot.workshop.services.exceptions;

public class DataIntegrityJpaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityJpaException(String msg) {
		super(msg);
	}
	
	public DataIntegrityJpaException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
