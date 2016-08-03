package com.senseinfosys.pubsense.gateway.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.senseinfosys.pubsense.gateway.domain.core.DomainException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DomainException.class)
	protected ResponseEntity<Object> handleInvalidRequest(DomainException e, ServletWebRequest request) {
		RestErrorMessage error = new RestErrorMessage(e.getErrorCode(), e.getErrorMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(e, error, headers, HttpStatus.OK, request);
	}
}
