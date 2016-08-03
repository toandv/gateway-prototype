package com.senseinfosys.pubsense.gateway.domain.user;

import com.senseinfosys.pubsense.gateway.domain.core.DomainException;

public class UserException extends DomainException {

	private static final long serialVersionUID = 5652095603078660512L;

	public UserException(int errorCode, String errorMessage, Throwable cause) {
		super(errorCode, errorMessage, cause);
	}

	public UserException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
