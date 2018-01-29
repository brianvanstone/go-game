package tech.notpaper.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.controller.exceptions.InvalidResponseException;
import tech.notpaper.go.controller.exceptions.NotFoundException;
import tech.notpaper.go.model.Command;
import tech.notpaper.go.model.Engine;
import tech.notpaper.go.model.Game;
import tech.notpaper.go.model.Response;
import tech.notpaper.go.pojo.CommandResponse;
import tech.notpaper.go.repository.CommandRepository;
import tech.notpaper.go.repository.EngineRepository;
import tech.notpaper.go.repository.GameRepository;
import tech.notpaper.go.rules.RulesProcessor;

@RestController
@RequestMapping("/go/api")
public class GTPController {
	
	@Autowired
	EngineRepository engineRepo;
	
	@Autowired
	GameRepository gameRepo;
	
	@Autowired
	CommandRepository commandRepo;
	
	/*
	 * Go protocol methods
	 */
	@GetMapping("/games/{id}/fetch")
	public ResponseEntity<Command> fetchCommand(@PathVariable("id") Long gameId,
												@RequestHeader("go-api-key") String apiKey)
																throws NotFoundException {
		Game game = getGame(gameId);
		Engine engine = getEngine(apiKey);
		Command command = getCommand(game, engine);
		
		return ResponseEntity.ok(command);
	}
	
	@PostMapping("/games/{id}/respond")
	public ResponseEntity<CommandResponse> postResponse(@PathVariable("id") Long gameId,
														@RequestBody Response response)
															throws InvalidResponseException {
		return ResponseEntity.ok(RulesProcessor.processResonseFor(response));
	}
	
	/*
	 * private helper methods
	 */
	private Engine getEngine(String apiKey) throws NotFoundException {
		Engine engine = engineRepo.findByApiKey(apiKey);
		if (engine == null) {
			throw new NotFoundException("Could not locate engine [apikey: " + apiKey + "]");
		}
		return engine;
	}
	
	private Game getGame(long gameId) throws NotFoundException {
		Game game = gameRepo.findOne(gameId);
		if (game == null) {
			throw new NotFoundException("Could not locate game [id: " + gameId + "]");
		}
		return game;
	}
	
	private Command getCommand(Game game, Engine engine) throws NotFoundException {
		Command command = commandRepo.findByEngine(engine);
		if (command == null || !command.getGame().getId().equals(game.getId())) {
			throw new NotFoundException("Could not locate command for "
										+ "game [id: " + command.getGame().getId() + "] "
										+ "and "
										+ "engine [id: " + engine.getId() + "]");
		}
		return command;
	}
}
