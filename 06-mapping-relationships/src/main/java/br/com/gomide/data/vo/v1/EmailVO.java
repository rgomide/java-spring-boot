package br.com.gomide.data.vo.v1;

import java.io.Serializable;

public class EmailVO implements Serializable {

  private Long id;
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
