package tech.notpaper.go.pojo;

import tech.notpaper.go.model.Response.ResponseStatus;

public class CommandResponse {
	private ResponseStatus status;
	private String message;
	private boolean boardStateChanged;
	private BoardState newBoardState;
	
	public CommandResponse() {
		this.status = ResponseStatus.SUCCESS;
		this.boardStateChanged = false;
	}
	
	public CommandResponse setStatus(ResponseStatus status) {
		this.status = status;
		return this;
	}
	
	public CommandResponse setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public CommandResponse setBoardStateChanged(boolean stateChange) {
		this.boardStateChanged = stateChange;
		return this;
	}
	
	public CommandResponse setNewBoardState(BoardState newBoardState) {
		this.newBoardState = newBoardState;
		return this;
	}
	
	public ResponseStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean isBoardStateChanged() {
		return boardStateChanged;
	}
	
	public BoardState getNewBoardState() {
		return newBoardState;
	}
}
