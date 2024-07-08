package com.bezkoder.spring.webflux.constantes;

public class ConstanteJwt {
	public static final long EXPIRATION_TIME_SECONDS = 60 * 60;
	public static final long EXPIRATION_TIME_MILISECONDS = 1000 * 60 * 60;
	public static final String SECRET = "ONPE_CLARIDAD";
	public static final String HEADER_STRING = "Authorization";
	public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String seguridadValidarProperties = "true";
}
