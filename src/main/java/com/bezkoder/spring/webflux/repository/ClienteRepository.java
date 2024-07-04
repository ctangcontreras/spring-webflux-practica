package com.bezkoder.spring.webflux.repository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.webflux.model.Cliente;

import reactor.core.publisher.Mono;


@Repository
public interface ClienteRepository extends R2dbcRepository<Cliente, Integer> {
    Mono<Cliente> findByCodigoUnico(String codigoUnico);
}


