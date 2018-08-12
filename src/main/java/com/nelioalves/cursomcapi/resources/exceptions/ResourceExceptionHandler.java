package com.nelioalves.cursomcapi.resources.exceptions;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.nelioalves.cursomcapi.services.exceptions.ObjectNotFoundException;

/**
 * Classe que vai filtrar as execoes lancadas nos meus recursos
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	/*
	 * Metodo que vai tratar excecoes desse tipo
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ResourceError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		ResourceError error = new ResourceError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}