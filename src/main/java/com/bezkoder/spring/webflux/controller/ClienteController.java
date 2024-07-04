package com.bezkoder.spring.webflux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.webflux.dto.ClienteDto;
import com.bezkoder.spring.webflux.service.ClienteService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteService clienteService;

 

    @RequestMapping(value = "/getCliente/{idCliente}", method = RequestMethod.GET)
    public Mono<ResponseEntity<ClienteDto>> getCliente(@PathVariable("idCliente") Integer idCliente) {
        return clienteService.listarClientePorId(idCliente)
                .map(clienteDto -> ResponseEntity.ok(clienteDto))
                .onErrorResume(e -> {
                    log.error(e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(500).body(new ClienteDto()));
                });
    }

    @RequestMapping(value = "/getInfo/{codigoUnico}", method = RequestMethod.GET)
    public Mono<ResponseEntity<ClienteDto>> getInfo(@PathVariable("codigoUnico") String codigoUnico) {
        return clienteService.getInfo(codigoUnico)
                .map(clienteDto -> ResponseEntity.ok(clienteDto))
                .onErrorResume(e -> {
                    log.error(e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(500).body(new ClienteDto()));
                });
    }
}
