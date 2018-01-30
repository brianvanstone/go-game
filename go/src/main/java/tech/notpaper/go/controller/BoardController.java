package tech.notpaper.go.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.go.model.Board;
import tech.notpaper.go.repository.BoardRepository;

@RestController
@RequestMapping("/go/api")
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/boards/{id}")
	public ResponseEntity<Board> board(@PathVariable("id") Long boardId) {
		return ResponseEntity.ok(boardRepo.findOne(boardId));
	}
	
	@GetMapping("/boards")
	public List<Board> boards() {
		return boardRepo.findAll();
	}
}
