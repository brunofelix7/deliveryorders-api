package com.nelioalves.cursomcapi.repositories;

import com.nelioalves.cursomcapi.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
