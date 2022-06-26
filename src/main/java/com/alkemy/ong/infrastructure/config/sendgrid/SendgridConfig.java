package com.alkemy.ong.infrastructure.config.sendgrid;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SendgridConfig {

  @Value("${sendgrid.sender}")
  private String sender;

  @Value("${sendgrid.key}")
  private String apiKey;

}
