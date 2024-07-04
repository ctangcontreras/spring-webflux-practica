package com.bezkoder.spring.webflux.service;

 
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.webflux.model.Usuario;
import com.bezkoder.spring.webflux.repository.UsuarioRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service 
public class UsuarioService {
    
    private final PasswordEncoder password = PasswordEncoderFactories.createDelegatingPasswordEncoder();

   
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Flux<Usuario> findAll () {
        return usuarioRepository.findAll();
    }

    public Mono<Usuario> findByUsername (String username) {
        return usuarioRepository.findByUsuario(username);
    }

    public Mono<Usuario> save (Usuario user) {
        user.setContrasenia(password.encode(user.getContrasenia()));
        return usuarioRepository.save(user);
    }

    public Mono<Void> deleteById (Integer id) {
        return usuarioRepository.deleteById(id);
    }
}
