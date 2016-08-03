package com.senseinfosys.pubsense.gateway.web;

import java.util.Date;

public class RestErrorMessage {

	private int code;

	private String message;

	private Date timestamp = new Date();

	public RestErrorMessage(int code, String message) {
		this.code = code;
		this.message = message;

	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

}
