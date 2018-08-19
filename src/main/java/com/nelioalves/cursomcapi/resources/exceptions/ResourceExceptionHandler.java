package com.nelioalves.cursomcapi.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.nelioalves.cursomcapi.services.exceptions.DataIntegrityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.nelioalves.cursomcapi.services.exceptions.ObjectNotFoundException;

import java.util.Date;

/**
 * Classe que vai filtrar as execoes lancadas nos meus recursos
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    /* Metodo que vai tratar excecoes desse tipo */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ResourceError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        ResourceError error = new ResourceError(HttpStatus.NOT_FOUND.value(), e.getMessage(), new Date(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<ResourceError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        ResourceError error = new ResourceError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), new Date(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
