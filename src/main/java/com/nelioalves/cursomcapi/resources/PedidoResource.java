package com.nelioalves.cursomcapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nelioalves.cursomcapi.domain.Pedido;
import com.nelioalves.cursomcapi.services.PedidoService;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> find(@PathVariable("id") Integer id){
		Pedido pedido = service.find(id);
		return ResponseEntity.ok().body(pedido);
	}

}
