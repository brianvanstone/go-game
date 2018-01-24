package tech.notpaper.go.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.notpaper.go.model.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {

}
