package com.senseinfosys.pubsense.gateway.infrastructure.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class StatelessTokenFilter extends GenericFilterBean {
	private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

	private final TokenService tokenService;

	public StatelessTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			// Already authenticated.
			filterChain.doFilter(request, response);
			return;
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		final String token = httpRequest.getHeader(AUTHORIZATION_HEADER_NAME);
		JwtSubject subject = tokenService.validateToken(token);
		Authentication auth = new AppAuthentication(subject.getAppId());
		SecurityContextHolder.getContext().setAuthentication(auth);
		// Token's valid, move on next filter in the chain.
		filterChain.doFilter(request, response);
	}
}
