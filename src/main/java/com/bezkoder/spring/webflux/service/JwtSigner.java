package com.bezkoder.spring.webflux.service;
 
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.webflux.model.MyUserDetails;
import com.bezkoder.spring.webflux.repository.UserDetailsRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ffzs
 * @Date: 2020/8/16 下午8:06
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtSigner {

    private final UserDetailsRepository myUserRepository;

    private final String key = "mySecretKey";
    private final String authorities = "authorities";
    private final String issuer = "yourIssuer";
    private final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.expiration.duration}")
    private int duration ;


    public String getAuthoritiesTag () {
        return authorities;
    }

    public String getIssuerTag () {
        return issuer;
    }

    public String getTokenPrefix () {
        return TOKEN_PREFIX;
    }

    public String generateToken (String username) {

        return generateToken(Objects.requireNonNull(myUserRepository.findByUsername(username).block()));
    }

    public String generateToken (MyUserDetails user) {

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim(authorities, user.getAuthorities())
                .signWith(SignatureAlgorithm.HS256,  key.getBytes())
                .setIssuer(issuer)
                .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(duration))))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .compact();
    }

    public Claims parseToken (String token) {
        log.info("token : {}", token);
        return Jwts
                .parser()
                .setSigningKey( key.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
