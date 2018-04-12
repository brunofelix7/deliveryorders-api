package com.nelioalves.cursomcapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nelioalves.cursomcapi.domain.Categoria;
import com.nelioalves.cursomcapi.services.CategoriaService;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		Categoria categoria = service.findById(id);
		return ResponseEntity.ok().body(categoria);
	}

}
