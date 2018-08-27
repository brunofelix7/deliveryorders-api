package com.nelioalves.cursomcapi.services;

import com.nelioalves.cursomcapi.dto.CategoriaDTO;
import com.nelioalves.cursomcapi.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomcapi.domain.Categoria;
import com.nelioalves.cursomcapi.repositories.CategoriaRepository;
import com.nelioalves.cursomcapi.services.exceptions.ObjectNotFoundException;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
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

    public Categoria save(Categoria obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.delete(id);
        } catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possuí produtos!");
        }
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

}
