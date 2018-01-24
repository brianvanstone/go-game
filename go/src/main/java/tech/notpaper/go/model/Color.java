package tech.notpaper.go.model;

public enum Color {
	WHITE, BLACK;
	
	@Override
	public String toString() {
		return this == WHITE ? "white" : "black";
	}
}
