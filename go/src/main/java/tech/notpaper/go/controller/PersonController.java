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

import tech.notpaper.go.model.Person;
import tech.notpaper.go.repository.EngineRepository;
import tech.notpaper.go.repository.PersonRepository;

@RestController
@RequestMapping("/go/api")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private EngineRepository engineRepo;
	
	@GetMapping("/people/{id}")
	public ResponseEntity<Person> person(@PathVariable("id") Long personId) {
		return ResponseEntity.ok(personRepo.findOne(personId));
	}
	
	@GetMapping("/people")
	public List<Person> people() {
		return personRepo.findAll();
	}
}
