package com.bezkoder.spring.webflux.constantes;

public class ConstanteApi {
	public static final String[] APIS_LIBRES = new String[] { 
			 "/auth/login"
			};

	public static final String[] URL_WEB_LIBRES = new String[] { 
			"/v2/api-docs",
			"/v3/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/swagger-resources/configuration/security",
			"/swagger-resources/configuration/ui",
			"/swagger-ui/index.html**", "/webjars/**", "/actuator/health",
			"/webjars",
			"/swagger-resources",
			"/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html","/swagger-ui/index.html",
			"/webjars/**" };
}
