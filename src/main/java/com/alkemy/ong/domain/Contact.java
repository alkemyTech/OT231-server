package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Contact {

  private String name;
  private String phone;
  private String email;
  private String message;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Contact)) {
      return false;
    }

    Contact contact = (Contact) o;

    return getEmail().equals(contact.getEmail());
  }

  @Override
  public int hashCode() {
    return getEmail().hashCode();
  }
}
