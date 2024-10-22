package br.com.gomide.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gomide.model.Phone;
import br.com.gomide.repositories.PhoneRepository;

@Service
public class PhoneService {

  @Autowired
  private PhoneRepository phoneRepository;

  public List<Phone> findAll() {
    return phoneRepository.findAll();
  }

  public Phone findById(Long id) {
    return phoneRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(
            "No records found for this ID"));
  }

  public Phone create(Phone phone) {
    return phoneRepository.save(phone);
  }

  public Phone update(Long id, Phone phone) {
    Phone entity = findById(id);
    entity.setNumber(phone.getNumber());
    return phoneRepository.save(entity);
  }

  public void delete(Long id) {
    Phone entity = findById(id);
    phoneRepository.delete(entity);
  }
}
