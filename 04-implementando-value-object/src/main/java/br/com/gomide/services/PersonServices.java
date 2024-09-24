package br.com.gomide.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gomide.data.vo.v1.PersonVO;
import br.com.gomide.data.vo.v2.PersonVOV2;
import br.com.gomide.exceptions.ResourceNotFoundException;
import br.com.gomide.mapper.DozerMapper;
import br.com.gomide.mapper.custom.PersonMapper;
import br.com.gomide.model.Person;
import br.com.gomide.repositories.PersonRepository;

@Service
public class PersonServices {

  private Logger logger = Logger.getLogger(PersonServices.class.getName());

  @Autowired
  PersonRepository repository;

  @Autowired
  PersonMapper mapper;

  public List<PersonVO> findAll() {
    logger.info("Method findAll started");

    return DozerMapper.parseListObjects(
        repository.findAll(),
        PersonVO.class);
  }

  public PersonVO findById(Long id) {
    logger.info("Method findById started");

    Person entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Person not found for id: %s", id)));

    return DozerMapper.parseObject(entity, PersonVO.class);
  }

  public PersonVO create(PersonVO person) {
    logger.info("Method create started");

    Person entity = DozerMapper.parseObject(person, Person.class);

    return DozerMapper.parseObject(
        repository.save(entity),
        PersonVO.class);
  }

  public PersonVOV2 createV2(PersonVOV2 person) {
    logger.info("Method create started with V2");

    Person entity = mapper.convertVoToEntity(person);
    PersonVOV2 vo = mapper.convertEntityToVo(repository.save(entity));

    return vo;
  }

  public PersonVO update(PersonVO person) {
    logger.info("Method update started");

    long id = person.getId();

    repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Person not found for id: %s", id)));

    Person entity = DozerMapper.parseObject(person, Person.class);

    return DozerMapper.parseObject(
        repository.save(entity),
        PersonVO.class);
  }

  public void delete(Long id) {
    logger.info("Method delete started");

    Person entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.format("Person not found for id: %s", id)));

    repository.delete(entity);
  }

}
