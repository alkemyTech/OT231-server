package com.alkemy.ong.infrastructure.config.sendgrid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "sendgrid")
@Configuration
@Data
public class SendgridConfig {

  private String sender;

  private String key;

}
