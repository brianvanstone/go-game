package tech.notpaper.go.rules;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

import tech.notpaper.go.controller.exceptions.InvalidResponseException;
import tech.notpaper.go.controller.exceptions.NotFoundException;
import tech.notpaper.go.controller.exceptions.UnsupportedCommandException;
import tech.notpaper.go.model.Command;
import tech.notpaper.go.model.Command.CommandStatus;
import tech.notpaper.go.model.Engine;
import tech.notpaper.go.model.Game;
import tech.notpaper.go.model.Game.GameStatus;
import tech.notpaper.go.model.Response;
import tech.notpaper.go.model.Response.ResponseStatus;
import tech.notpaper.go.pojo.CommandResponse;
import tech.notpaper.go.repository.*;

public class RulesProcessor {
	
	private BoardRepository boardRepo;
	private CommandRepository commandRepo;
	private EngineRepository engineRepo;
	private GameRepository gameRepo;
	private PersonRepository personRepo;
	private ResponseRepository responseRepo;
	
	private static final Set<String> requiredCommands = new HashSet<String>() {{
		add("protocol_version");
		add("name");
		add("version");
		add("known_command");
		add("list_commands");
		add("quit");
		add("boardsize");
		add("clear_board");
		add("komi");
		add("play");
		add("genmove");
	}};

	public RulesProcessor(BoardRepository boardRepo,
							CommandRepository commandRepo,
							EngineRepository engineRepo,
							GameRepository gameRepo,
							PersonRepository personRepo,
							ResponseRepository responseRepo) {
		this.boardRepo = boardRepo;
		this.commandRepo = commandRepo;
		this.engineRepo = engineRepo;
		this.gameRepo = gameRepo;
		this.personRepo = personRepo;
		this.responseRepo = responseRepo;
	}
	
	public CommandResponse processResonseFor(Response response) throws InvalidResponseException {
		Command command;
		try {
			command = getCommand(response.getId());
		} catch (NotFoundException e) {
			throw new InvalidResponseException("Response ID must match to Command ID. " + e.getMessage(), e);
		}
		
		try {
			switch(command.getCommandType()) {
			case PROTO_V:
				return processResponseForProtoVersion(command, response);
			case NAME:
				return processResponseForName(command, response);
			case VERSION:
				return processResponseForVersion(command, response);
			case KNOWN_CMD:
				return processResponseForKnownCommand(command, response);
			case BOARDSIZE:
				return processResponseForBoardsize(command, response);
			case CLEAR_BOARD:
				return processResponseForClearBoard(command, response);
			case GENMOVE:
				return processResponseForGenmove(command, response);
			case KOMI:
				return processResponseForKomi(command, response);
			case LIST_CMDS:
				return processResponseForListCommands(command, response);
			case PLAY:
				return processResponseForPlay(command, response);
			case QUIT:
				return processResponseForQuit(command, response);
			default:
				throw new InvalidResponseException("Response provided is invalid");
			}
		} catch (AssertionError e) {
			return new CommandResponse()
					.setStatus(ResponseStatus.FAILURE)
					.setMessage(e.getMessage());
		}
	}
	
	private CommandResponse processResponseForProtoVersion(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		Assert.assertEquals("GTP Proto version must be 2", "2", response.getResponse());
		
		command.setStatus(CommandStatus.COMPLETED);
		
		commandRepo.save(command);
		responseRepo.save(response);
		
		r.setMessage("Protocol version verified");
		
		return r;
	}
	
	private CommandResponse processResponseForName(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		Engine engine = command.getEngine();
		engine.setName(response.getResponse());
		Assert.assertEquals("Unable to update name for engine", response.getResponse(), engine.getName());
		
		command.setStatus(CommandStatus.COMPLETED);
		
		engineRepo.save(engine);
		commandRepo.save(command);
		
		r.setMessage("Engine name updated");
		
		return r;
	}
	
	private CommandResponse processResponseForVersion(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		Engine engine = command.getEngine();
		engine.setVersion(response.getResponse());
		Assert.assertEquals("Unable to udpate version for engine", response.getResponse(), engine.getVersion());
		
		command.setStatus(CommandStatus.COMPLETED);
		
		engineRepo.save(engine);
		commandRepo.save(command);
		responseRepo.save(response);
		
		r.setMessage("Engine version updated");
		
		return r;
	}
	
	private CommandResponse processResponseForKnownCommand(Command command, Response response) {
		throw new UnsupportedCommandException("known_command not currently supported");
		/*CommandResponse r = new CommandResponse();
		
		try {
			
		} catch (AssertionError e) {
			
		}
		
		return r;*/
	}
	
	private CommandResponse processResponseForListCommands(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		Set<String> commands = new HashSet<>(Arrays.asList(response.getResponse().split(" ")));
		List<String> missingCommands = new LinkedList<>();
		
		for(String reqCommand : requiredCommands) {
			if (!commands.contains(reqCommand)) {
				missingCommands.add(reqCommand);
			}
		}
		
		Assert.assertEquals("Required commands are missing: " + missingCommands, 0, missingCommands.size());
		
		command.setStatus(CommandStatus.COMPLETED);
		
		commandRepo.save(command);
		responseRepo.save(response);
		
		r.setMessage("Command list verified");
		
		return r;
	}
	
	private CommandResponse processResponseForQuit(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		Game game = command.getGame();
		game.setStatus(GameStatus.COMPLETE);
		Assert.assertEquals("Unable to quit the game", GameStatus.COMPLETE, game.getGameStatus());
		
		command.setStatus(CommandStatus.COMPLETED);
		Assert.assertEquals("Unable to update Command status", CommandStatus.COMPLETED, command.getStatus());
		
		gameRepo.save(game);
		commandRepo.save(command);
		responseRepo.save(response);
		
		r.setMessage("Game quit successfully");
		
		return r;
	}
	
	private CommandResponse processResponseForBoardsize(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		try {
			
		} catch (AssertionError e) {
			
		}
		
		return r;
	}
	
	private CommandResponse processResponseForClearBoard(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		try {
			
		} catch (AssertionError e) {
			
		}
		
		return r;
	}
	
	private CommandResponse processResponseForKomi(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		try {
			
		} catch (AssertionError e) {
			
		}
		
		return r;
	}
	
	private CommandResponse processResponseForPlay(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		try {
			
		} catch (AssertionError e) {
			
		}
		
		return r;
	}
	
	private CommandResponse processResponseForGenmove(Command command, Response response) {
		CommandResponse r = new CommandResponse();
		
		try {
			
		} catch (AssertionError e) {
			
		}
		
		return r;
	}
	
	private Command getCommand(Long id) throws NotFoundException {
		Command command = commandRepo.findOne(id);
		if (command == null) {
			throw new NotFoundException("Could not locate command [id: " + id + "]");
		}
		return command;
	}
}
