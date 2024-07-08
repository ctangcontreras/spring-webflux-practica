package com.bezkoder.spring.webflux.security;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bezkoder.spring.webflux.constantes.ConstanteJwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenProvider {

	public String obtenerSoloToken(String token) {
		String tokenOnly = token.substring(ConstanteJwt.BEARER_TOKEN_PREFIX.length());
		return tokenOnly;
	}
	
	public String generarRefreshToken(final Map<String, Object> claims, String ruc) {
		long nowMillis = System.currentTimeMillis();
		Date horaActual = new Date(nowMillis);

		long expMillis = nowMillis + ConstanteJwt.EXPIRATION_TIME_MILISECONDS;
		Date exp = new Date(expMillis);

		return Jwts.builder().setClaims(claims).setSubject(ruc).setIssuedAt(horaActual).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, ConstanteJwt.SECRET).compact();
	}
	
	public Claims validateAuthToken(final String token) {
		return getClaimsFromAuthToken(token);
	}
	
	Claims getClaimsFromAuthToken(String token) {
		return Jwts.parser().setSigningKey(ConstanteJwt.SECRET).parseClaimsJws(token).getBody();
	}
}
