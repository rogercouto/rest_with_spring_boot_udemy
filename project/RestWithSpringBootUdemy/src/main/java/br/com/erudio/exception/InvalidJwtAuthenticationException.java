package br.com.erudio.exception;

import javax.security.sasl.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public InvalidJwtAuthenticationException(String message) {
		super(message);
	}
	
}
