package com.senseinfosys.pubsense.gateway.domain.app;

import com.senseinfosys.pubsense.gateway.domain.core.DomainException;

public class AppException extends DomainException {

	public AppException(int errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}
	
	public AppException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

	private static final long serialVersionUID = -4340400200619806772L;

}
