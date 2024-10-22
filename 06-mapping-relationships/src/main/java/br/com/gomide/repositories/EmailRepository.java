package br.com.gomide.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gomide.model.Email;

@Repository
public interface EmailRepository
    extends JpaRepository<Email, Long> {

  List<Email> findByPersonId(Long personId);

}
