package br.com.gomide.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gomide.model.Phone;

public interface PhoneRepository
    extends JpaRepository<Phone, Long> {

}
