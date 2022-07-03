package com.alkemy.ong.application.util.template;

import com.alkemy.ong.application.util.IEmail;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WelcomeTemplateMail implements IEmail {

  private String logo;
  private final OrganizationEntity organization;
  private final UserEntity user;

  @Override
  public String getSubject() {
    return organization.getName();
  }

  @Override
  public String getTo() {
    return user.getEmail();
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
