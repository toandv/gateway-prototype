package com.senseinfosys.pubsense.gateway.infrastructure.json;

import com.senseinfosys.pubsense.gateway.domain.core.DomainException;

public class JsonException extends DomainException {

	private static final long serialVersionUID = 1008117256405035757L;

	public JsonException(int errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

	public JsonException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
