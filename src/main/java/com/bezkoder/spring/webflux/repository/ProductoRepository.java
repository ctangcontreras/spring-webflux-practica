package com.bezkoder.spring.webflux.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.webflux.model.Producto;

import reactor.core.publisher.Flux;

@Repository
public interface ProductoRepository extends R2dbcRepository<Producto, Integer>{
    
    Flux<Producto> findByIdCliente(Integer idCliente);
}
