package tech.notpaper.go.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidStateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6545266442938645168L;
	
	public InvalidStateException(String message) {
		super(message);
	}
	
	public InvalidStateException(String message, Throwable cause) {
		super(message, cause);
	}

}
