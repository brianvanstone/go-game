package tech.notpaper.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.notpaper.sample.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
