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
    return "Contact successfully registered";
  }

  @Override
  public String getTo() {
    return getEmail();
  }

  @Override
  public String getContent() {
    return "Thanks for contacting us. We will back to you soon!";
  }

  @Override
  public String getContentType() {
    return "text/plain";
  }
}
