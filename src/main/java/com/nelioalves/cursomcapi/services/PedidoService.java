package com.nelioalves.cursomcapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomcapi.domain.Pedido;
import com.nelioalves.cursomcapi.repositories.PedidoRepository;
import com.nelioalves.cursomcapi.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;

	public Pedido findById(Integer id){
		Pedido obj = repository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
}
