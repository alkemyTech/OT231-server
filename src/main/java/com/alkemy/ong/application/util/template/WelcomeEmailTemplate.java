package com.alkemy.ong.application.util.template;

import com.alkemy.ong.application.util.IContactInfo;
import com.alkemy.ong.application.util.IEmail;
import com.alkemy.ong.domain.Organization;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WelcomeEmailTemplate implements IEmail {

  private final Organization organization;
  private final IContactInfo contactInfo;

  @Override
  public String getSubject() {
    return organization.getName();
  }

  @Override
  public String getTo() {
    return contactInfo.getEmail();
  }

  @Override
  public String getContent() {
    return organization.getWelcomeText();
  }

  @Override
  public String getContentType() {
    return "text/plain";
  }

}
