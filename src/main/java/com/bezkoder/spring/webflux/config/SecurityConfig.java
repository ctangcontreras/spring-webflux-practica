package com.bezkoder.spring.webflux.config;


 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.bezkoder.spring.webflux.handler.JwtWebFilter;
import com.bezkoder.spring.webflux.security.SecurityContextRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
 
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
 @Configuration
public class SecurityConfig {

    private final SecurityContextRepository securityRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
                ServerHttpSecurity http,
                JwtWebFilter jwtWebFilter
        ) {

        return http
                .authorizeExchange()
                .pathMatchers("/auth/login", "/auth/signup","**/cliente/getInfo/**").permitAll()
                .pathMatchers("/v3/api-docs/**", 
                "/swagger-resources/configuration/ui",
                "/swagger-doc/v3/api-docs/swagger-config",
                "/swagger-doc/v3/api-docs",
                "/swagger-doc/swagger-ui.html",
                "/swagger-doc/webjars/swagger-ui/index.html",
                "**/swagger-doc/**",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html",
                "/css/**", 
                "/js/**",
                "/images/**", 
                "/webjars/**", 
                "/swagger-ui/**", 
                "/swagger-doc/webjars/swagger-ui/favicon-32x32.png", 
                "**/favicon.ico", 
                "/index").permitAll()
                .anyExchange().authenticated()
                .and()
                .addFilterAfter(jwtWebFilter, SecurityWebFiltersOrder.FIRST)   
                .securityContextRepository(securityRepository)
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .logout().disable()
                .build();
    }
    
}
