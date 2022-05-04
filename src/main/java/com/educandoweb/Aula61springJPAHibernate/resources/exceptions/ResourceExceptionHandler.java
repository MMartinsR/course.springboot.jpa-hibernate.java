package com.educandoweb.Aula61springJPAHibernate.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.Aula61springJPAHibernate.services.exceptions.ResourceNotFoundException;

// Anotação responsável por interceptar as possíveis exceptions que esta classe poderá tratar.
@ControllerAdvice
public class ResourceExceptionHandler {
	
	// Método responsável por tratar o erro ResourceNotFound, utilizando como modelo de 
	// resposta, a classe StandardError criada anteriormente.
	// Vamos usar ainda uma anotação que será capaz de fazer essa interceptação das exceções.
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
