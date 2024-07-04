package com.bezkoder.spring.webflux.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.webflux.dto.ClienteDto;
import com.bezkoder.spring.webflux.model.HttpResult;
import com.bezkoder.spring.webflux.model.LoginResponse;
import com.bezkoder.spring.webflux.model.MyUserDetails;
import com.bezkoder.spring.webflux.repository.UserDetailsRepository;
import com.bezkoder.spring.webflux.service.JwtSigner;
import com.bezkoder.spring.webflux.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    private final UserDetailsRepository myUserRepository;
    private final UsuarioService myUserService;
    private final JwtSigner jwtSigner;
    private final PasswordEncoder password = PasswordEncoderFactories.createDelegatingPasswordEncoder();

 

    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Mono<HttpResult> getCliente(@RequestBody Map<String, String> user) {
     ObjectMapper mapper = new ObjectMapper();
        System.out.println(user.get("username"));
        System.out.println(user.get("password")); 
 

        return Mono.just(user.get("username"))
                .flatMap(myUserRepository::findByUsername)
                .doOnNext(i -> log.info("{}", i))
                //.filter(it -> password.matches(user.get("password"), it.getPassword()))
                .filter(it -> user.get("password").equals(it.getPassword()))
                .map(it -> {
                    try {
                        return new HttpResult(HttpStatus.OK.value(),
                                "ok",
                                new LoginResponse(it.getUsername(),
                                        mapper.writeValueAsString(it
                                                .getAuthorities()
                                                .stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .collect(Collectors.toList())),
                                        jwtSigner.generateToken(it)));
                    } catch (JsonProcessingException e) {
                        return new HttpResult();
                    }
                })
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.just(new HttpResult(HttpStatus.UNAUTHORIZED.value(), "No autorizado", null)));
    }
 
}
