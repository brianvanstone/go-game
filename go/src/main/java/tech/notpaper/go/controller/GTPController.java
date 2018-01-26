package tech.notpaper.go.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.controller.exceptions.NotFoundException;
import tech.notpaper.go.model.Command;
import tech.notpaper.go.model.Engine;
import tech.notpaper.go.model.Game;
import tech.notpaper.go.model.Response;
import tech.notpaper.go.model.Command.CommandStatus;
import tech.notpaper.go.repository.CommandRepository;
import tech.notpaper.go.repository.EngineRepository;
import tech.notpaper.go.repository.GameRepository;

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
		
		Command command = commandRepo.findOne(
				Example.of(new Command()
						.setEngine(engine)
						.setStatus(CommandStatus.PENDING)));
		if (command == null) {
			throw new NotFoundException("No command currently available. Try again later");
		}
		
		return ResponseEntity.ok(command);
	}
	
	@PostMapping("/games/{id}/respond")
	public ResponseEntity<Object> postResponse(@PathVariable("id") Long gameId,
											   @RequestBody Response response) {
		return null;
	}
	
	/*
	 * private helper methods
	 */
	private Engine getEngine(String apiKey) throws NotFoundException {
		Engine engine = engineRepo.findOne(Example.of(new Engine().setApiKey(apiKey)));
		if (engine == null) {
			throw new NotFoundException("Could not find engine with api key: " + apiKey);
		}
		return engine;
	}
	
	private Game getGame(long gameId) throws NotFoundException {
		Game game = gameRepo.findOne(gameId);
		if (game == null) {
			throw new NotFoundException("Could not locate game with id: " + gameId);
		}
		return game;
	}
}
