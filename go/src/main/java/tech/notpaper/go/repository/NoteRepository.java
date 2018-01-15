package tech.notpaper.go.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.notpaper.go.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
