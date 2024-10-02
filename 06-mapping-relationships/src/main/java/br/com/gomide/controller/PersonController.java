package br.com.gomide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gomide.data.vo.v1.EmailVO;
import br.com.gomide.data.vo.v1.PersonVO;
import br.com.gomide.mapper.DozerMapper;
import br.com.gomide.model.Email;
import br.com.gomide.model.Person;
import br.com.gomide.services.PersonServices;

@RestController
@RequestMapping("/api/people/v1")
public class PersonController {

  @Autowired
  private PersonServices service;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<PersonVO> findAll() {
    return DozerMapper.parseListObjects(
        service.findAll(),
        PersonVO.class);
  }

  @GetMapping(value = "/{id}/emails", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<EmailVO> listEmails(@PathVariable(value = "id") Long id) {
    Person person = service.findById(id);
    List<Email> emails = person.getEmails();

    return DozerMapper.parseListObjects(
        emails,
        EmailVO.class);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public PersonVO findById(@PathVariable(value = "id") Long id) {
    return DozerMapper.parseObject(
        service.findById(id),
        PersonVO.class);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public PersonVO create(@RequestBody PersonVO person) {
    Person entity = service.create(
        DozerMapper.parseObject(person, Person.class));

    return DozerMapper.parseObject(entity, PersonVO.class);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public PersonVO update(@RequestBody PersonVO person) {
    Person entity = service.update(
        DozerMapper.parseObject(person, Person.class));

    return DozerMapper.parseObject(entity, PersonVO.class);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
