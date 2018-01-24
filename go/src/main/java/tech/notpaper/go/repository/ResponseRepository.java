package tech.notpaper.go.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.notpaper.go.model.Response;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

}
