package tech.notpaper.go.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.controller.exceptions.NotFoundException;
import tech.notpaper.go.model.Board;
import tech.notpaper.go.model.Command;
import tech.notpaper.go.model.Engine;
import tech.notpaper.go.model.Game;
import tech.notpaper.go.model.Person;
import tech.notpaper.go.pojo.Color;
import tech.notpaper.go.repository.BoardRepository;
import tech.notpaper.go.repository.CommandRepository;
import tech.notpaper.go.repository.EngineRepository;
import tech.notpaper.go.repository.GameRepository;
import tech.notpaper.go.repository.PersonRepository;
import tech.notpaper.go.repository.ResponseRepository;

@RestController
@RequestMapping("/go/api")
public class GameController {
	
	@Autowired
	BoardRepository boardRepo;
	
	@Autowired
	EngineRepository engineRepo;
	
	@Autowired
	GameRepository gameRepo;
	
	@Autowired
	PersonRepository personRepo;
	
	@Autowired
	CommandRepository commandRepo;
	
	@Autowired
	ResponseRepository responseRepo;
	
	/*
	 * Test method for whatever I want
	 */
	@GetMapping("/test")
	public ResponseEntity<Game> test() {
		
		Person brian = new Person().withName("Brian")
								   .withBio("This is my first person");
		personRepo.save(brian);
		Engine p1 = new Engine().withOwner(brian);
		engineRepo.save(p1);
		
		Person suzy = new Person().withName("Suzy")
								  .withBio("This is my second person");
		personRepo.save(suzy);
		Engine p2 = new Engine().withOwner(suzy);
		engineRepo.save(p2);
		
		Board board = new Board().ofSize(19);
		boardRepo.save(board);
		
		Command command = Command.genmove(Color.BLACK).setEngine(p1);
		command = commandRepo.save(command);
		
		Game game = new Game().withBoard(board)
							  .betweenPlayers(p1, p2)
							  .addCommand(command);
		gameRepo.save(game);
		
		return ResponseEntity.ok(gameRepo.findOne(game.getId()));
	}
	
	/*
	 * Gets for Game model
	 */
	@GetMapping("/games/{id}")
	public ResponseEntity<Game> game(@PathVariable("id") Long gameId,
									 @RequestHeader("go-api-key") String apiKey) throws NotFoundException {
		Game game = getGame(gameId);
		
		if (game.getPlayerOne().apikey().equals(apiKey) ||
				game.getPlayerTwo().apikey().equals(apiKey) ||
				game.getPlayerOne().getOwner().apikey().equals(apiKey) ||
				game.getPlayerTwo().getOwner().apikey().equals(apiKey)) {
			return ResponseEntity.ok(game);
		} else {
			throw new NotFoundException("Could not locate game with id: " + gameId);
		}
	}
	
	@GetMapping("/games")
	public List<Game> games(@RequestHeader("go-api-key") String apiKey) throws NotFoundException {
		try {
			Engine engine = getEngine(apiKey);
			return gameRepo
					.findAll()
					.stream()
					.filter(g -> g.getPlayerOne().getId().equals(engine.getId()) ||
								 g.getPlayerTwo().getId().equals(engine.getId()))
					.collect(Collectors.toList());
		} catch (NotFoundException e) {
			try {
				Person person = getPerson(apiKey);
				return gameRepo
						.findAll()
						.stream()
						.filter(g -> g.getPlayerOne().getOwner().getId().equals(person.getId()) ||
									 g.getPlayerTwo().getOwner().getId().equals(person.getId()))
						.collect(Collectors.toList());
			} catch (NotFoundException e2) {
				throw e;
			}
		}
		
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
	
	private Person getPerson(String apiKey) throws NotFoundException {
		Person person = personRepo.findOne(Example.of(new Person().setApiKey(apiKey)));
		if (person == null) {
			throw new NotFoundException("Could not find engine with api key: " + apiKey);
		}
		return person;
	}
	
	private Game getGame(long gameId) throws NotFoundException {
		Game game = gameRepo.findOne(gameId);
		if (game == null) {
			throw new NotFoundException("Could not locate game with id: " + gameId);
		}
		return game;
	}
}
