package tech.notpaper.go.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.notpaper.go.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
