package com.bezkoder.spring.webflux.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

 
@Component
@AllArgsConstructor
@Slf4j
public class SecurityContextRepository implements ServerSecurityContextRepository {

    private final JwtAuthenticationManager jwtAuthenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @SneakyThrows
    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        log.info(" ServerSecurityContextRepository  。。。。。。。。。。。");

        String token = exchange.getAttribute("token");
        return jwtAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(token, token)
            )
            .map(SecurityContextImpl::new);
    }
}
