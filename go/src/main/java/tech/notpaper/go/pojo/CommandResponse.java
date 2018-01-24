package tech.notpaper.go.pojo;

public class CommandResponse {
	
	private String reason;
	private String message;
	private Exception causedBy;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getCausedBy() {
		return causedBy;
	}

	public void setCausedBy(Exception causedBy) {
		this.causedBy = causedBy;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
