package tech.notpaper.go.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.notpaper.go.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
