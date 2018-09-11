package com.nelioalves.cursomcapi.services;

import com.nelioalves.cursomcapi.domain.Cliente;
import com.nelioalves.cursomcapi.dto.ClienteDTO;
import com.nelioalves.cursomcapi.repositories.ClienteRepository;
import com.nelioalves.cursomcapi.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomcapi.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Transactional
	public Cliente save(Cliente obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
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

	public Cliente find(Integer id){
		Cliente obj = repository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
	
	public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }
    
    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }
    
    private void updateData(Cliente newObj, Cliente obj) {
    	newObj.setNome(obj.getNome());
    	newObj.setEmail(obj.getEmail());
	}

}
