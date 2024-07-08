package com.bezkoder.spring.webflux.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bezkoder.spring.webflux.constantes.ConstanteApi;
import com.bezkoder.spring.webflux.constantes.ConstanteJwt;
import com.bezkoder.spring.webflux.excepciones.ExceptionResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTTokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//logger.info("FILTROS - INI: " + request.getRequestURI());
		response.setHeader("X-FRAME-OPTIONS", "DENY");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "180");
		
		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		try {
			String uri = request.getRequestURI();
			if (esUrlLibre(uri, request)) {
				filterChain.doFilter(request, response);
			}else {
                if (Boolean.parseBoolean(ConstanteJwt.seguridadValidarProperties)) {
                                        if (checkJWTToken(request)) {
                                            String jwt = getJWTFromRequest(request);
                                            Claims claims = tokenProvider.validateAuthToken(jwt);
                                            if (claims.get("numeroDocumento") != null || claims.get("idUsuario") != null) {
                                                Authentication auth = new UsernamePasswordAuthenticationToken(claims.get("idUsuario"),null,null);
                                                SecurityContextHolder.getContext().setAuthentication(auth);
                                                filterChain.doFilter(request, response);
                                            } else {
                                                System.out.println("--------");
                                                System.out.println("El token no cuenta con la clave principal.");
                                                System.out.println("--------");
                                                SecurityContextHolder.clearContext();
                                                validarError(response, "El token no cuenta con la clave principal.");
                                            }
                                        }else {
                                            System.out.println("--------");
                                            System.out.println(request.getRequestURI()+": Se requiere token para acceder al servicio.");
                                            System.out.println("--------");
                                            SecurityContextHolder.clearContext();
                                            validarError(response, "Se requiere token para acceder al servicio.");
                                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                        }
                                    }else {
                                        System.out.println("SIGPE - INGRESANDO SIN TOKEN");
                                        logger.info("SIGPE - request"+request.getRequestURI());
                                        filterChain.doFilter(request, response);
                                    }
				
			}
		} catch (ExpiredJwtException e) {
			String isRefreshToken = request.getHeader("isRefreshToken");
			String requestURL = request.getRequestURL().toString();
			if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
				allowForRefreshToken(e, request);
			} else {
				request.setAttribute("exception", e);
				logger.error("El token ha expirado.", e);
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			}
		} catch (UnsupportedJwtException e) {
			logger.error("No se puede detectar la clave del token.", e);
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("El token esta mal formado.", e);
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			validarError(response, "Ocurrió un error interno.");
		}		
	}
	
	private void validarError(HttpServletResponse response, String mensaje) throws IOException {
		ExceptionResponse error = new ExceptionResponse();
		error.setMensaje(mensaje);
		error.setMensajeInteno(mensaje);
		error.setResultado(-1);

		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		String errorJson = gson.toJson(error);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(errorJson);
	}
	
	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		request.setAttribute("claims", ex.getClaims());
	}
	
	private Boolean esUrlLibre(String url, HttpServletRequest request) {
		String urlTmp = "";
		for (String urlLibre : ConstanteApi.URL_WEB_LIBRES) {
			urlTmp = request.getContextPath() + urlLibre;
			if (url.contains(urlTmp)) {
				return true;
			}
		}
		for (String urlLibre : ConstanteApi.APIS_LIBRES) {
			urlTmp = request.getContextPath() + urlLibre;
			if (url.contains(urlTmp)) {
				return true;
			}
		}
		return false;
	}
	
	private String getJWTFromRequest(HttpServletRequest request) {
		final String bearerToken = request.getHeader(ConstanteJwt.HEADER_STRING);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(ConstanteJwt.BEARER_TOKEN_PREFIX)) {
			return bearerToken.substring(ConstanteJwt.BEARER_TOKEN_PREFIX.length());
		}
		throw new MalformedJwtException("Authentication Header doesn't have Bearer Token");
	}

	private boolean checkJWTToken(HttpServletRequest request) {
		final String authenticationHeader = request.getHeader(ConstanteJwt.HEADER_STRING);
		return authenticationHeader != null && authenticationHeader.startsWith(ConstanteJwt.BEARER_TOKEN_PREFIX);
	}	
}

