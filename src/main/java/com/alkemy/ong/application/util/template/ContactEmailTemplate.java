package com.alkemy.ong.application.util.template;

import com.alkemy.ong.application.util.IContactInfo;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContactEmailTemplate implements IContactInfo {

  private final User user;

  @Override
  public String getEmail() {
    return user.getEmail();
  }

  @Override
  public String getFullName() {
    return user.getFirstName() + " " + user.getLastName();
  }

}
