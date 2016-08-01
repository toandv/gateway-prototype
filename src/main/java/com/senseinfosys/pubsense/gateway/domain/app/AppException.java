package com.senseinfosys.pubsense.gateway.domain.app;

import com.senseinfosys.pubsense.gateway.domain.core.DomainException;

public class AppException extends DomainException {

	private static final long serialVersionUID = -4340400200619806772L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

}
