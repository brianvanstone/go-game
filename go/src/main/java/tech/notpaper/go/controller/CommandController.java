package tech.notpaper.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.model.Command;
import tech.notpaper.go.repository.CommandRepository;

@RestController
@RequestMapping("/go/api")
public class CommandController {
	
	@Autowired
	private CommandRepository commandRepo;
	
	@GetMapping("/commands/{id}")
	public ResponseEntity<Command> command(@PathVariable("id") Long commandId) {
		return ResponseEntity.ok(commandRepo.findOne(commandId));
	}
	
	@GetMapping("/commands")
	public List<Command> commands() {
		return commandRepo.findAll();
	}
}
