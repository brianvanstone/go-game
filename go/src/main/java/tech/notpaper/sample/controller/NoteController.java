package tech.notpaper.sample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.notpaper.sample.model.Note;
import tech.notpaper.sample.repository.NoteRepository;

@RestController
@RequestMapping("/notes/api")
public class NoteController {
	
	@Autowired
	NoteRepository noteRepo;
	
	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		return noteRepo.findAll();
	}
	
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepo.save(note);
	}
	
	@GetMapping("/notes/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable("id") Long noteId) {
		Note note = noteRepo.findOne(noteId);
		
		if(note == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(note);
	}
	
	@PutMapping("/notes/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable("id") Long noteId,
										   @Valid @RequestBody Note noteDetails) {
		Note note = noteRepo.findOne(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}
		
		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());
		
		Note updatedNote = noteRepo.save(note);
		return ResponseEntity.ok(updatedNote);
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable("id") Long noteId) {
		Note note = noteRepo.findOne(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}
		
		noteRepo.delete(note);
		return ResponseEntity.ok().build();
	}
}
