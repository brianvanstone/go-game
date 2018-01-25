package tech.notpaper.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.model.Board;
import tech.notpaper.go.model.Color;
import tech.notpaper.go.model.Command;
import tech.notpaper.go.model.Engine;
import tech.notpaper.go.model.Game;
import tech.notpaper.go.model.Person;
import tech.notpaper.go.model.Response;
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
	 * Go protocol methods
	 */
	@GetMapping("/games/{id}/fetch")
	public ResponseEntity<Command> fetchCommand(@PathVariable("id") Long gameId) {
		Game game = gameRepo.findOne(gameId);
		
		return ResponseEntity.ok(game.getCommand());
	}
	
	@PostMapping("/respond")
	public ResponseEntity<Response> postResponse() {
		return null;
	}
	
	/*
	 * Gets for Game model
	 */
	@GetMapping("/games/{id}")
	public ResponseEntity<Game> game(@PathVariable("id") Long gameId) {
		return ResponseEntity.ok(gameRepo.findOne(gameId));
	}
	
	@GetMapping("/games")
	public List<Game> games() {
		return gameRepo.findAll();
	}
}
