package br.com.gomide.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gomide.exceptions.ResourceNotFoundException;
import br.com.gomide.model.Email;
import br.com.gomide.repositories.EmailRepository;

@Service
public class EmailService {

  @Autowired
  EmailRepository emailRepository;

  public List<Email> findByPersonId(Long personId) {
    return emailRepository.findByPersonId(personId);
  }

  public Email create(Long personId, Email email) {
    email.setPersonId(personId);
    return emailRepository.save(email);
  }

  public Email update(Email email) {
    long id = email.getId();

    emailRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Email not found for id: %s", id)));

    return emailRepository.save(email);
  }

  public void delete(Long id) {
    emailRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Email not found for id: %s", id)));

    emailRepository.deleteById(id);
  }

}
