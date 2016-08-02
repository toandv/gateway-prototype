package com.senseinfosys.pubsense.gateway.infrastructure.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import com.senseinfosys.pubsense.gateway.domain.app.AppService;
import com.senseinfosys.pubsense.gateway.domain.app.Application;
import com.senseinfosys.pubsense.gateway.domain.user.UserService;

public class StatelessTokenFilter extends GenericFilterBean {
	private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

	private TokenService tokenService;

	private UserService userService;

	private AppService appService;

	public StatelessTokenFilter(TokenService tokenService, UserService userService, AppService appService) {
		this.tokenService = tokenService;
		this.userService = userService;
		this.appService = appService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String header = httpRequest.getHeader(AUTHORIZATION_HEADER_NAME);
		if (header == null) {
			throw new BadCredentialsException("No authorization token.");
		}
		Authentication authentication = null;
		if (header.startsWith("Basic ")) {
			String uri = httpRequest.getRequestURI();
			String[] basicTokens = getBasicTokens(header);
			if (uri.endsWith("register")) {
				com.senseinfosys.pubsense.gateway.domain.user.User user = userService
						.findByUsernameAndPassword(basicTokens[0], basicTokens[1]);
				if (user == null) {
					// TODO
					throw new BadCredentialsException("Invalid authorization token.");
				}
				authentication = getAuthentication(user);
			} else if (uri.endsWith("token")) {
				Application app = appService.find(basicTokens[0], basicTokens[1]);
				if (app == null) {
					// TODO
					throw new BadCredentialsException("Invalid authorization token.");
				}
				authentication = getAppAuthentication(app.getId());
			}

		} else if (header.startsWith("Bearer ")) {
			String bearerToken = getBearerTokens(header);
			JwtSubject subject = tokenService.validateToken(bearerToken);
			authentication = getAppAuthentication(subject.getAppId());
		} else {
			throw new BadCredentialsException("Invalid authorization token.");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Token's valid, move on next filter in the chain.
		filterChain.doFilter(request, response);
	}

	private Authentication getAppAuthentication(String appId) {
		return new AppAuthentication(appId);
	}

	private Authentication getAuthentication(com.senseinfosys.pubsense.gateway.domain.user.User user) {
		return new UserAuthentication(new User(user.getUsername(), user.getPassword(), user.rolesToAuthorities()));
	}

	private String getBearerTokens(String header) {
		return header.substring(7);
	}

	private String[] getBasicTokens(String header) throws UnsupportedEncodingException {
		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.getDecoder().decode(base64Token);
		} catch (Exception e) {
			throw new BadCredentialsException("Failed to decode authorization token.");
		}

		String token = new String(decoded, "UTF-8");
		int delim = token.indexOf(":");
		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authorization token.");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}
}
