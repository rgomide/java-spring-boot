package br.com.gomide.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.gomide.model.Person;

@Service
public class PersonServices {

  private final AtomicLong counter = new AtomicLong();
  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  public List<Person> findAll() {
    logger.info("Method findAll started");

    List<Person> persons = new ArrayList<Person>();

    for (int i = 0; i < 8; i++) {
      Person person = mockPerson(i);
      persons.add(person);
    }

    return persons;
  }

  public Person findById(String id) {
    logger.info("Method findById started");

    Person person = new Person();

    person.setId(counter.incrementAndGet());
    person.setFirstName("Denecley");
    person.setLasttName("Alvim");
    person.setGender("Male");
    person.setAddress("Faina - GO");

    return person;
  }

  public Person create(Person person) {
    logger.info("Method create started");

    return person;
  }

  public Person update(Person person) {
    logger.info("Method update started");
    
    return person;
  }
  
  public void delete(String id) {
    logger.info("Method delete started");
  }

  private Person mockPerson(int i) {
    Person person = new Person();

    person.setId(counter.incrementAndGet());
    person.setFirstName("Person Name" + i);
    person.setLasttName("Last Name" + i);
    person.setGender("Male");
    person.setAddress("Some Address in Brasil" + i);

    return person;
  }

}
