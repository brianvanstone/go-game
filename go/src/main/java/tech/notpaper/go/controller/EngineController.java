package tech.notpaper.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.model.Engine;
import tech.notpaper.go.repository.EngineRepository;

@RestController
@RequestMapping("/go/api")
public class EngineController {
	
	@Autowired
	private EngineRepository engineRepo;
	
	@GetMapping("/engines/{id}")
	public ResponseEntity<Engine> engine(@PathVariable("id") Long engineId) {
		return ResponseEntity.ok(engineRepo.findOne(engineId));
	}
	
	@GetMapping("/engines")
	public List<Engine> engines() {
		return engineRepo.findAll();
	}
	
	public static Engine humanEngine() {
		return new Engine().setName("Human").setDescription("Used when placing calls to the api as the user");
	}
}
