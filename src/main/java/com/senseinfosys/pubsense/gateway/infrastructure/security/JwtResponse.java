package com.senseinfosys.pubsense.gateway.infrastructure.security;

import java.util.Date;

public class JwtResponse {

	private String token;

	private Date expiredAt;

	public JwtResponse(String token, Date expiredAt) {
		this.token = token;
		this.expiredAt = expiredAt;
	}

	public JwtResponse() {
	}

	public String getToken() {
		return token;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

}
