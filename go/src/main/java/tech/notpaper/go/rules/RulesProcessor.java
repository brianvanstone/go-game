package tech.notpaper.go.rules;

import tech.notpaper.go.controller.exceptions.InvalidResponseException;
import tech.notpaper.go.model.Command;
import tech.notpaper.go.model.Response;
import tech.notpaper.go.pojo.CommandResponse;

public class RulesProcessor {
	
	public static CommandResponse processResonseFor(Response response) throws InvalidResponseException {
		Command command = response.getCommand();
		
		switch(command.getCommandType()) {
		case PROTO_V:
			return processResponseForProtoVersion(response);
		case NAME:
			return processResponseForName(response);
		case VERSION:
			return processResponseForVersion(response);
		case KNOWN_CMD:
			return processResponseForKnownCommand(response);
		case BOARDSIZE:
			return processResponseForBoardsize(response);
		case CLEAR_BOARD:
			return processResponseForClearBoard(response);
		case GENMOVE:
			return processResponseForGenmove(response);
		case KOMI:
			return processResponseForKomi(response);
		case LIST_CMDS:
			return processResponseForListCommands(response);
		case PLAY:
			return processResponseForPlay(response);
		case QUIT:
			return processResponseForQuit(response);
		default:
			throw new InvalidResponseException("Response provided is invalid");
		}
	}
	
	private static CommandResponse processResponseForProtoVersion(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForName(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForVersion(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForKnownCommand(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForListCommands(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForQuit(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForBoardsize(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForClearBoard(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForKomi(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForPlay(Response response) {
		return null;
	}
	
	private static CommandResponse processResponseForGenmove(Response response) {
		return null;
	}
}
