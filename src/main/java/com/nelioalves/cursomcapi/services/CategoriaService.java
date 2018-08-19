package com.nelioalves.cursomcapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomcapi.domain.Categoria;
import com.nelioalves.cursomcapi.repositories.CategoriaRepository;
import com.nelioalves.cursomcapi.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	private final CategoriaRepository repository;

	@Autowired
	public CategoriaService(CategoriaRepository repository) {
		this.repository = repository;
	}

	public Categoria find(Integer id){
		Categoria obj = repository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

	public Categoria save(Categoria obj){
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj){
		find(obj.getId());
		return repository.save(obj);
	}
}
