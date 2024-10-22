package br.com.gomide.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "number", nullable = false, length = 15)
  private String number;

  @OneToMany(mappedBy = "phone")
  private List<PersonPhone> peoplePhones;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public List<PersonPhone> getPeoplePhones() {
    return peoplePhones;
  }

  public void setPeoplePhones(
      List<PersonPhone> peoplePhones) {
    this.peoplePhones = peoplePhones;
  }

}
