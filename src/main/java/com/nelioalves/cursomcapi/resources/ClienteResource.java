package com.nelioalves.cursomcapi.resources;

import com.nelioalves.cursomcapi.domain.Cliente;
import com.nelioalves.cursomcapi.dto.ClienteDTO;
import com.nelioalves.cursomcapi.services.ClienteService;
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

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {
	
	private final ClienteService service;
	
	@Autowired
	public ClienteResource(ClienteService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody ClienteDTO clienteDTO){
		Cliente cliente = service.fromDTO(clienteDTO);
		cliente = service.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable("id") Integer id){
		Cliente cliente = service.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = service.update(cliente);
		return ResponseEntity.ok().body(cliente);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		Cliente cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> list(){
		List<ClienteDTO> clientes = service.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientes);
	}

	@GetMapping(path = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<ClienteDTO> clientes = service.findPage(page, linesPerPage, orderBy, direction).map(ClienteDTO::new);
		return ResponseEntity.ok().body(clientes);
	}

}
