package com.alkemy.ong.infrastructure.util;

import com.alkemy.ong.application.exception.ThirdPartyException;
import com.alkemy.ong.application.util.IEmail;
import com.alkemy.ong.application.util.ISendEmail;
import com.alkemy.ong.infrastructure.config.sendgrid.SendgridConfig;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendEmailDelegate implements ISendEmail {

  @Autowired
  private SendgridConfig sendgridConfig;

  @Override
  public void execute(IEmail email) {
    Email fromEmail = new Email(sendgridConfig.getSender());
    Email toEmail = new Email(email.getTo());
    Content mailContent = new Content(
            email.getContentType(),
            email.getContent()
    );

    Mail mail = new Mail(fromEmail, email.getSubject(), toEmail, mailContent);
    SendGrid sendGrid = new SendGrid(sendgridConfig.getKey());
    try {
      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sendGrid.api(request);
      log.info("Sendgrid Status Code: " + response.getStatusCode());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ThirdPartyException(e.getMessage());
    }
  }

  @Override
  public String getType() {
    return "SENDGRID";
  }
}
