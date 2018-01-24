package tech.notpaper.go.pojo;

public class Response {
	private boolean success;
	private int id;
	private String response;
	
	public String getRaw() {
		StringBuilder sb = new StringBuilder();
		if (success) {
			sb.append("=");
		} else {
			sb.append("?");
		}
		
		if (id > 0) {
			sb.append(id);
		}
		
		sb.append(response);
		sb.append("\n\n");
		return sb.toString();
	}
}
