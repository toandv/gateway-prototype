package com.senseinfosys.pubsense.gateway.infrastructure.json;

import com.senseinfosys.pubsense.gateway.domain.core.DomainException;

public class JsonException extends DomainException {

	private static final long serialVersionUID = 1008117256405035757L;

	public JsonException(String message, Throwable cause) {
		super(message, cause);
	}

}
