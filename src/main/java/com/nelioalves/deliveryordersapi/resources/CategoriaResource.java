package com.nelioalves.deliveryordersapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.deliveryordersapi.domain.Categoria;
import com.nelioalves.deliveryordersapi.dto.CategoriaDTO;
import com.nelioalves.deliveryordersapi.services.CategoriaService;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {
	
	private final CategoriaService service;

	@Autowired
	public CategoriaResource(CategoriaService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CategoriaDTO categoriaDTO){
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria = service.save(categoria);
		//	Retorna a nova URI criada com o id
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(categoria);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable("id") Integer id){
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoriaDTO.setId(id);
		categoria = service.update(categoria);
		return ResponseEntity.ok().body(categoriaDTO);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id){
		Categoria categoria = service.find(id);
		return ResponseEntity.ok().body(categoria);
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> list(){
		//	Converte a lista de Domain em uma lista DTO
		//	List<CategoriaDTO> categorias = service.findAll().stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		List<CategoriaDTO> categorias = service.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(categorias);
	}

	@GetMapping(path = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<CategoriaDTO> categorias = service.findPage(page, linesPerPage, orderBy, direction).map(CategoriaDTO::new);
		return ResponseEntity.ok().body(categorias);
	}

}
