package tech.notpaper.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.model.Board;
import tech.notpaper.go.model.Command;
import tech.notpaper.go.model.Engine;
import tech.notpaper.go.model.Game;
import tech.notpaper.go.model.Message;
import tech.notpaper.go.model.Person;
import tech.notpaper.go.model.Response;
import tech.notpaper.go.repository.BoardRepository;
import tech.notpaper.go.repository.EngineRepository;
import tech.notpaper.go.repository.GameRepository;
import tech.notpaper.go.repository.PersonRepository;

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
		
		Game game = new Game().withBoard(board)
							  .betweenPlayers(p1, p2);
		gameRepo.save(game);
		return ResponseEntity.ok(game);
	}
	
	/*
	 * Go protocol methods
	 */
	@GetMapping("/fetch")
	public ResponseEntity<Command> fetchCommand(@RequestBody Command command) {
		return null;
	}
	
	@PostMapping("/respond")
	public ResponseEntity<Response> postResponse() {
		return null;
	}
	
	/*
	 * Gets for all models. Eventually will filter results by api key
	 */

	@GetMapping("/games/{id}")
	public ResponseEntity<Game> fetchCommand(@PathVariable("id") Long gameId) {
		return ResponseEntity.ok(gameRepo.findOne(gameId));
	}
	
	@GetMapping("/games")
	public List<Game> games() {
		return gameRepo.findAll();
	}
	
	@GetMapping("/people/{id}")
	public ResponseEntity<Person> person(@PathVariable("id") Long personId) {
		return ResponseEntity.ok(personRepo.findOne(personId));
	}
	
	@GetMapping("/people")
	public List<Person> people() {
		return personRepo.findAll();
	}
	
	@GetMapping("/engines/{id}")
	public ResponseEntity<Engine> engine(@PathVariable("id") Long engineId) {
		return ResponseEntity.ok(engineRepo.findOne(engineId));
	}
	
	@GetMapping("/engines")
	public List<Engine> engines() {
		return engineRepo.findAll();
	}
	
	@GetMapping("/boards/{id}")
	public ResponseEntity<Board> board(@PathVariable("id") Long boardId) {
		return ResponseEntity.ok(boardRepo.findOne(boardId));
	}
	
	@GetMapping("/boards")
	public List<Board> boards() {
		return boardRepo.findAll();
	}
}
