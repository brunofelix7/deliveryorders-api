package com.nelioalves.deliveryordersapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.deliveryordersapi.domain.Pedido;
import com.nelioalves.deliveryordersapi.repositories.PedidoRepository;
import com.nelioalves.deliveryordersapi.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;

	public Pedido find(Integer id){
		Pedido obj = repository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
}
