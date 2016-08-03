package com.senseinfosys.pubsense.gateway.domain.core;

public class DomainException extends RuntimeException {

	protected int errorCode = 0;

	private String errorMessage = "Unknown.";

	private static final long serialVersionUID = -4209559241545600860L;

	public DomainException(int errorCode, String errorMessage, Throwable cause) {
		super(cause.getMessage(), cause);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public DomainException(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
