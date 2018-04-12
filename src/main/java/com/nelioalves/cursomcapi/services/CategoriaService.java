package com.nelioalves.cursomcapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomcapi.domain.Categoria;
import com.nelioalves.cursomcapi.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	public Categoria findById(Integer id){
		Categoria categoria = repository.findOne(id);
		return categoria;
	}
}
