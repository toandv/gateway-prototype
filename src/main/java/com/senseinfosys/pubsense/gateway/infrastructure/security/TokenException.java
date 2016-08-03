package com.senseinfosys.pubsense.gateway.infrastructure.security;

import com.senseinfosys.pubsense.gateway.domain.core.DomainException;

public class TokenException extends DomainException {

	private static final long serialVersionUID = 1296717076321471486L;

	public TokenException(int errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

	public TokenException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
