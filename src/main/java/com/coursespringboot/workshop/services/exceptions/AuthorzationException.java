package com.coursespringboot.workshop.services.exceptions;

public class AuthorzationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AuthorzationException(String msg) {
		super(msg);
	}
	
	public AuthorzationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
