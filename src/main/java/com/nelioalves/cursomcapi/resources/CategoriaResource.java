package com.nelioalves.cursomcapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nelioalves.cursomcapi.domain.Categoria;
import com.nelioalves.cursomcapi.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {
	
	private final CategoriaService service;

	@Autowired
	public CategoriaResource(CategoriaService service) {
		this.service = service;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id){
		Categoria categoria = service.find(id);
		return ResponseEntity.ok().body(categoria);
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> list(){
		List<Categoria> categorias = service.findAll();
		return ResponseEntity.ok().body(categorias);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Categoria categoria){
		categoria = service.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@RequestBody Categoria categoria, @PathVariable("id") Integer id){
		categoria.setId(id);
		categoria = service.update(categoria);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
