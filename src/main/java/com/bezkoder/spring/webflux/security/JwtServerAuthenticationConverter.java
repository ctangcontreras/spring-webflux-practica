package com.bezkoder.spring.webflux.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

 
@Component
@AllArgsConstructor
@Slf4j
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {

    private final String TOKEN_PREFIX = "Bearer ";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        log.info("访问 ServerAuthenticationConverter  。。。。。。。。。。。");
        if (exchange.getRequest().getPath().value().contains("auth/login")) return Mono.empty();

        return Mono.justOrEmpty(exchange)
                .flatMap(it -> {
                    var auth = it.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
                    if (auth == null) {
                        return Mono.error(new BadCredentialsException(" authorization"));
                    }
                    return Mono.just(auth);
                })
                .filter(it -> {
                    if (!it.isEmpty()) return true;
                    else {
                        throw new BadCredentialsException("falta token");
                    }
                })
                .log()
                .map(it -> it.get(0))
                .filter(it -> {
                    if (it.startsWith(TOKEN_PREFIX)) return true;
                    else {
                        throw new BadCredentialsException("token" + TOKEN_PREFIX + "");
                    }
                })
                .map(it -> it.replace(TOKEN_PREFIX, ""))
                .map(it -> {
                    Authentication auth = new UsernamePasswordAuthenticationToken(it, it);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    return auth;
                });
    }
}
