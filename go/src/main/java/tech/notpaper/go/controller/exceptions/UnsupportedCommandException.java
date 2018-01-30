package tech.notpaper.go.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedCommandException extends UnsupportedOperationException {
	
	private static final long serialVersionUID = 2538705288942038417L;

	public UnsupportedCommandException(String message) {
		super(message);
	}
	
	public UnsupportedCommandException(String message, Throwable cause) {
		super(message, cause);
	}
}
