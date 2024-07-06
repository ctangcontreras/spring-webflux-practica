package com.bezkoder.spring.webflux.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.webflux.model.Empleado;

import reactor.core.publisher.Flux;

@Repository
public interface EmpleadoRepository extends R2dbcRepository<Empleado, Integer> {
    
    @Query("SELECT * FROM Empleado WHERE estado = 1")
    Flux<Empleado> listarEmpleadosActivos();
}
