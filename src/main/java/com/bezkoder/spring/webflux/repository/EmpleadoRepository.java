package com.bezkoder.spring.webflux.repository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.webflux.model.Empleado;


@Repository
public interface EmpleadoRepository extends R2dbcRepository<Empleado, Integer> {
    
}


