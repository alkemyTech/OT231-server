package com.alkemy.ong.application.template;

import com.alkemy.ong.application.util.IEmail;
import com.alkemy.ong.application.util.ISendEmail;
import com.alkemy.ong.domain.Organization;

public class MailTemplateWelcome implements IEmail, ISendEmail {

  private String logo;
  private Organization organization;
  private String welcomeText;
  //TODO: ver los datos de contacto si hace falta hacer otra clase que los contenga
  @Override
  public String getSubject() {
    return null;
  }

  @Override
  public String getTo() {
    return null;
  }

  @Override
  public String getContent() {
    return null;
  }

  @Override
  public String getContentType() {
    return null;
  }

  @Override
  public void execute(IEmail email) {

  }

  @Override
  public String getType() {
    return null;
  }
}
