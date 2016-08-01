package com.senseinfosys.pubsense.gateway.infrastructure.security;

import com.senseinfosys.pubsense.gateway.domain.core.DomainException;

public class TokenException extends DomainException {

	private static final long serialVersionUID = 1296717076321471486L;

	public TokenException() {
		super();
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenException(String message) {
		super(message);
	}

	public TokenException(Throwable cause) {
		super(cause);
	}

}
