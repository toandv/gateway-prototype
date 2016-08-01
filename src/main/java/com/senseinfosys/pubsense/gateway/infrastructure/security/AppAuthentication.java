package com.senseinfosys.pubsense.gateway.infrastructure.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AppAuthentication implements Authentication {

	private static final long serialVersionUID = 4569259191013858943L;

	private String appId;

	public AppAuthentication(String appId) {
		this.appId = appId;
	}

	@Override
	public String getName() {
		return appId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public Object getCredentials() {
		return appId;
	}

	@Override
	public Object getDetails() {
		return appId;
	}

	@Override
	public Object getPrincipal() {
		return appId;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

	}

}
