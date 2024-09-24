package br.com.gomide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gomide.data.vo.v2.PersonVOV2;
import br.com.gomide.services.PersonServices;

@RestController
@RequestMapping("/api/people/v2")
public class PersonV2Controller {

  @Autowired
  private PersonServices service;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
    return service.createV2(person);
  }

}
