package tech.notpaper.go.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.notpaper.go.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
