package tech.notpaper.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.model.Response;
import tech.notpaper.go.repository.ResponseRepository;

@RestController
@RequestMapping("/go/api")
public class ResponseController {
	@Autowired
	private ResponseRepository responseRepo;
	
	@GetMapping("/responses/{id}")
	public ResponseEntity<Response> response(@PathVariable("id") Long personId) {
		return ResponseEntity.ok(responseRepo.findOne(personId));
	}
	
	@GetMapping("/responses")
	public List<Response> responses() {
		return responseRepo.findAll();
	}
}
