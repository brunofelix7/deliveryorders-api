package com.nelioalves.deliveryordersapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.deliveryordersapi.domain.Categoria;
import com.nelioalves.deliveryordersapi.dto.CategoriaDTO;
import com.nelioalves.deliveryordersapi.repositories.CategoriaRepository;
import com.nelioalves.deliveryordersapi.services.exceptions.DataIntegrityException;
import com.nelioalves.deliveryordersapi.services.exceptions.ObjectNotFoundException;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria save(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
    	Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }
    
    public void delete(Integer id) {
        find(id);
        try {
            repository.delete(id);
        } catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque existem entidades relacionadas!");
        }
    }
    
    public Categoria find(Integer id) {
        Categoria obj = repository.findOne(id);
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
        }
        return obj;
    }

    public List<Categoria> findAll() {
        return repository.findAll();
    }

    /**
     * Retorna as categorias em paginação
     */
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    /**
     * Converte uma CategoriaDTO em Categoria
     */
    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }
    
    private void updateData(Categoria newObj, Categoria obj) {
    	newObj.setNome(obj.getNome());
	}

}
