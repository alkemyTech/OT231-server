package com.alkemy.ong.application.util.template;

import com.alkemy.ong.application.util.IContactInfo;
import com.alkemy.ong.application.util.IEmail;
import com.alkemy.ong.domain.Contact;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmationEmailTemplate implements IContactInfo, IEmail {

  private final Contact contact;

  @Override
  public String getEmail() {
    return contact.getEmail();
  }

  @Override
  public String getFullName() {
    return contact.getName();
  }

  @Override
  public String getSubject() {
    return "esto es un asunto de confirmacion";
  }

  @Override
  public String getTo() {
    return getEmail();
  }

  @Override
  public String getContent() {
    return "Confirmation Contact!";
  }

  @Override
  public String getContentType() {
    return "text/html";
  }
}
