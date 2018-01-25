package tech.notpaper.go.pojo;

import java.io.Serializable;
import java.util.Date;

public class Error implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5751198123063442736L;
	
	private int status;
	private Date timestamp;
	private String error;
	private String exception;
	private String message;
	private String path;
	
	public int getStatus() {
		return status;
	}
	
	public Error setStatus(int status) {
		this.status = status;
		return this;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public Error setTimestamp(Date d) {
		this.timestamp = d;
		return this;
	}
	
	public String getError() {
		return error;
	}
	
	public Error setError(String error) {
		this.error = error;
		return this;
	}
	
	public String getException() {
		return exception;
	}
	
	public Error setException(String exception) {
		this.exception = exception;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Error setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public Error setPath(String path) {
		this.path = path;
		return this;
	}
	
	public String getPath() {
		return path;
	}
	
	public static Error fromException(Exception e) {
		return new Error()
				.setMessage(e.getMessage())
				.setException(e.getClass().getCanonicalName());
	}
}
