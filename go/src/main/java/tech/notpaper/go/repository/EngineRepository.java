package tech.notpaper.go.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.notpaper.go.model.Engine;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
	public Engine findByApiKey(String apikey);
}
