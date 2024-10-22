package br.com.gomide.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "people_phones")
public class PersonPhone {
  @EmbeddedId
  private PersonPhoneId id;

  @ManyToOne
  @MapsId("personId")
  @JoinColumn(name = "person_id")
  private Person person;

  @ManyToOne
  @MapsId("phoneId")
  @JoinColumn(name = "phone_id")
  private Phone phone;

  @Column(name = "\"primary\"")
  private boolean primary;

  public PersonPhone() {}

  public PersonPhone(Long personId, Long phoneId,
      boolean primary) {
    this.id = new PersonPhoneId(personId, phoneId);

    this.setPerson(new Person());
    this.setPhone(new Phone());

    this.person.setId(personId);
    this.phone.setId(phoneId);

    this.primary = primary;
  }

  public PersonPhoneId getId() {
    return id;
  }

  public void setId(PersonPhoneId id) {
    this.id = id;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Phone getPhone() {
    return phone;
  }

  public void setPhone(Phone phone) {
    this.phone = phone;
  }

  public boolean isPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

}
