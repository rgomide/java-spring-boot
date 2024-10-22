package br.com.gomide.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PersonPhoneId implements Serializable {

  private Long personId;
  private Long phoneId;

  public PersonPhoneId() {}

  public PersonPhoneId(Long personId, Long phoneId) {
    this.personId = personId;
    this.phoneId = phoneId;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  public Long getPhoneId() {
    return phoneId;
  }

  public void setPhoneId(Long phoneId) {
    this.phoneId = phoneId;
  }

}
