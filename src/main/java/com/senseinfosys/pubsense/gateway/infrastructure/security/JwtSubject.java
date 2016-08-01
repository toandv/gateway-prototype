package com.senseinfosys.pubsense.gateway.infrastructure.security;

public class JwtSubject {

	private String appId;

	public JwtSubject() {
	}

	public JwtSubject(String appId) {
		this.appId = appId;
	}

	public String getAppId() {
		return appId;
	}

}
