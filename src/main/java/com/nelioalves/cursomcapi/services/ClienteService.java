package com.nelioalves.cursomcapi.services;

import com.nelioalves.cursomcapi.domain.Cliente;
import com.nelioalves.cursomcapi.repositories.ClienteRepository;
import com.nelioalves.cursomcapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Integer id){
		Cliente obj = repository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
}
