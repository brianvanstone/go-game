package tech.notpaper.go.pojo;

import java.util.List;

public class Command {
	private int id;
	private CommandType command;
	private List<String> args;
	
	public Command(CommandType command) {
		this.command = command;
	}
	
	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}

	public void setCommand(CommandType command) {
		this.command = command;
	}

	public enum CommandType {
		PROTO_V, NAME, VERSION, KNOWN_CMD, LIST_CMDS, QUIT, BOARDSIZE, CLEAR_BOARD, KOMI, PLAY, GENMOVE;
		
		@Override
		public String toString() {
			switch(this) {
			case PROTO_V:
				return "protocol_version";
			case NAME:
				return "name";
			case VERSION:
				return "version";
			case KNOWN_CMD:
				return "known_command";
			case LIST_CMDS:
				return "list_commands";
			case QUIT:
				return "quit";
			case BOARDSIZE:
				return "boardsize";
			case CLEAR_BOARD:
				return "clear_board";
			case KOMI:
				return "komi";
			case PLAY:
				return "play";
			case GENMOVE:
				return "genmove";
				default:
					return super.toString();
			}
		}
	}
	
	public int getId() {
		return id;
	}
	
	public CommandType getCommand() {
		return command;
	}
	
	public List<String> getArguments() {
		return args;
	}
	
	public String getRaw() {
		StringBuilder sb = new StringBuilder();
		if (id > 0) {
			sb.append(id);
		}
		
		sb.append(command);
		sb.append(String.join(" ", args));
		sb.append("\n");
		return sb.toString();
	}
}
