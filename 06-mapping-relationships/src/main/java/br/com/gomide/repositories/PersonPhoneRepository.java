package br.com.gomide.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gomide.model.PersonPhone;
import br.com.gomide.model.PersonPhoneId;

public interface PersonPhoneRepository
    extends JpaRepository<PersonPhone, PersonPhoneId> {

  public List<PersonPhone> findByPersonId(Long personId);

}
