package tech.notpaper.go.pojo;

import java.util.regex.Pattern;

public class Location {
	
	private String x;
	private String y;
	
	private static Pattern xPattern = Pattern.compile("[a-zA-Z]{1}");
	private static Pattern yPattern = Pattern.compile("[0-9]{,2}");
	
	public Location setX(String x) {
		//if x is a single char in range a-zA-Z
		if (xPattern.matcher(x).matches()) {
			this.x = x;
		} else {
			throw new IllegalArgumentException("Invalid argument [" + x + "]");
		}
		return this;
	}
	
	public Location setY(String y) {
		if (yPattern.matcher(y).matches()) {
			this.y = y;
		} else {
			throw new IllegalArgumentException("Invalid argument [" + y + "]");
		}
		return this;
	}
	
	public String getLocationString() {
		return x + y;
	}
	
	public String getX() {
		return x;
	}
	
	public String getY() {
		return y;
	}
}
