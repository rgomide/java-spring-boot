package br.com.gomide.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gomide.model.PersonPhone;
import br.com.gomide.model.PersonPhoneId;
import br.com.gomide.repositories.PersonPhoneRepository;

@Service
public class PersonPhoneService {

  @Autowired
  private PersonPhoneRepository repository;

  public List<PersonPhone> findByPersonId(Long personId) {
    return repository.findByPersonId(personId);
  }

  public PersonPhone create(PersonPhone entity) {
    return repository.save(entity);
  }

  public void delete(Long personId, Long phoneId) {
    repository
        .deleteById(new PersonPhoneId(personId, phoneId));
  }
}
