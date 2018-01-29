package tech.notpaper.go.pojo;

import tech.notpaper.go.model.Command.CommandStatus;

public class CommandResponse {
	private CommandStatus status;
	private String message;
	private boolean stateChange;
	private BoardState newBoardState;
	
	public CommandResponse setStatus(CommandStatus status) {
		this.status = status;
		return this;
	}
	
	public CommandResponse setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public CommandResponse setStateChange(boolean stateChange) {
		this.stateChange = stateChange;
		return this;
	}
	
	public CommandResponse setNewBoardState(BoardState newBoardState) {
		this.newBoardState = newBoardState;
		return this;
	}
	
	public CommandStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean isStateChange() {
		return stateChange;
	}
	
	public BoardState getNewBoardState() {
		return newBoardState;
	}
}
