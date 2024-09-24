package br.com.gomide.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gomide.exceptions.ResourceNotFoundException;
import br.com.gomide.mapper.custom.PersonMapper;
import br.com.gomide.model.Person;
import br.com.gomide.repositories.PersonRepository;

@Service
public class PersonServices {

  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  @Autowired
  PersonRepository repository;

  @Autowired
  PersonMapper mapper;

  public List<Person> findAll() {
    logger.info("Method findAll started");

    return repository.findAll();
  }

  public Person findById(Long id) {
    logger.info("Method findById started");

    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Person not found for id: %s", id)));
  }

  public Person create(Person person) {
    logger.info("Method create started");

    return repository.save(person);
  }

  public Person update(Person person) {
    logger.info("Method update started");

    long id = person.getId();

    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Person not found for id: %s", id)));

    return repository.save(person);
  }

  public void delete(Long id) {
    logger.info("Method delete started");

    Person entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Person not found for id: %s", id)));

    repository.delete(entity);
  }

}
