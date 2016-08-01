package com.senseinfosys.pubsense.gateway.domain.core;

public class DomainException extends RuntimeException {

	private static final long serialVersionUID = -4209559241545600860L;

	public DomainException() {
	}

	public DomainException(String message, Throwable cause) {
		super(message, cause);
	}

	public DomainException(String message) {
		super(message);
	}

	public DomainException(Throwable cause) {
		super(cause);
	}

}
