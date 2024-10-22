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
import br.com.gomide.mapper.custom.EmailMapper;
import br.com.gomide.model.Email;
import br.com.gomide.services.EmailService;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

  @Autowired
  private EmailService emailService;

  @GetMapping(value = "/people/{personId}/emails", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<EmailVO> findByPerson(@PathVariable(value = "personId") Long personId) {
    List<Email> emails = emailService.findByPersonId(personId);
    return EmailMapper.convertEntityToVO(emails);
  }

  @PostMapping(value = "/people/{personId}/emails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public EmailVO create(@PathVariable(value = "personId") Long personId, @RequestBody EmailVO emailVO) {
    Email email = EmailMapper.convertVOToEntity(emailVO);
    email = emailService.create(personId, email);
    return EmailMapper.convertEntityToVO(email);
  }

  @PutMapping(value = "/emails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public EmailVO update(@RequestBody EmailVO emailVO) {
    Email email = EmailMapper.convertVOToEntity(emailVO);
    email = emailService.update(email);
    return EmailMapper.convertEntityToVO(email);
  }

  @DeleteMapping(value = "/emails/{id}")
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
    emailService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
