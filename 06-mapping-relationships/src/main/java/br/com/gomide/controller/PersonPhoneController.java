package br.com.gomide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gomide.data.vo.v1.PhoneVO;
import br.com.gomide.mapper.custom.PhoneMapper;
import br.com.gomide.model.PersonPhone;
import br.com.gomide.services.PersonPhoneService;

@RestController
@RequestMapping("/api/v1")
public class PersonPhoneController {

  @Autowired
  private PersonPhoneService service;

  @GetMapping("/people/{personId}/phones")
  public List<PhoneVO> findByPersonId(
      @PathVariable Long personId) {
    List<PersonPhone> entities =
        service.findByPersonId(personId);
    return PhoneMapper.convertEntityToVO(entities);
  }

  @PostMapping("/people/{personId}/phones/{phoneId}")
  public PhoneVO create(@PathVariable Long personId,
      @PathVariable Long phoneId, @RequestBody PhoneVO vo) {
    PersonPhone entity =
        new PersonPhone(personId, phoneId, vo.getPrimary());
    entity = service.create(entity);

    return PhoneMapper.convertEntityToVO(entity);
  }

  @DeleteMapping("/people/{personId}/phones/{phoneId}")
  public ResponseEntity<?> delete(
      @PathVariable Long personId,
      @PathVariable Long phoneId) {
    service.delete(personId, phoneId);

    return ResponseEntity.noContent().build();
  }

}
