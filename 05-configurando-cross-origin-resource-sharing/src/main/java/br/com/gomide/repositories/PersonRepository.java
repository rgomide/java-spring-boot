package br.com.gomide.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gomide.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
