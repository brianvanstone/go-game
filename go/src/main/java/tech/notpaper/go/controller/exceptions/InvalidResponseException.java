package tech.notpaper.go.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidResponseException extends Exception {
	
	private static final long serialVersionUID = -8142019519879674749L;

	public InvalidResponseException(String message) {
		super(message);
	}
	
	public InvalidResponseException(String message, Throwable cause) {
		super(message, cause);
	}
}
