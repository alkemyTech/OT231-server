package com.alkemy.ong.application.util;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendEmailDelegate implements ISendEmail {

  @Value("${sendgrid.sender}")
  private String sender;

  @Value("${sendgrid.key}")
  private String apiKey;

  @Override
  public String execute(IEmail email) {
    Email fromEmail = new Email(sender);
    Email toEmail = new Email(email.getTo());
    Content mailContent = new Content(
            email.getContentType(),
            email.getContent()
    );

    Mail mail = new Mail(fromEmail, email.getSubject(), toEmail, mailContent);
    SendGrid sendGrid = new SendGrid(apiKey);
    String resume = "";
    try {
      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sendGrid.api(request);
      resume = "Status Code: " + response.getStatusCode()
              + " Body: " + response.getBody()
              + " Heaeder: " + response.getHeaders();
    } catch (IOException e) {
      System.out.println("Error trying to send the email.");
    }
    return resume;
  }

  @Override
  public String getType() {
    return "SENDGRID";
  }
}
