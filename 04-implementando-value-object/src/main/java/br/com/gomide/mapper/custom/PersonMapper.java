package br.com.gomide.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.gomide.data.vo.v2.PersonVOV2;
import br.com.gomide.model.Person;


@Service
public class PersonMapper {

  public static PersonVOV2 convertEntityToVo(Person person) {
    PersonVOV2 vo = new PersonVOV2();

    vo.setId(person.getId());
    vo.setFirstName(person.getFirstName());
    vo.setLastName(person.getLastName());
    vo.setAddress(person.getAddress());
    vo.setGender(person.getGender());

    vo.setBirthDay(new Date());

    return vo;
  }

  public static Person convertVoToEntity(PersonVOV2 person) {
    Person entity = new Person();

    entity.setId(person.getId());
    entity.setFirstName(person.getFirstName());
    entity.setLastName(person.getLastName());
    entity.setAddress(person.getAddress());
    entity.setGender(person.getGender());

    return entity;
  }

}
