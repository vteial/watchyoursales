package io.vteial.watchyoursales.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends
		ResponseEntityExceptionHandler {

	public RestResponseEntityExceptionHandler() {
		super();
	}

	// 500
	@ExceptionHandler({ NullPointerException.class,
			IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<Object> handleInternal(final RuntimeException ex,
			final WebRequest request) {
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
