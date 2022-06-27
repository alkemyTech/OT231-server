package com.alkemy.ong.infrastructure.config.sendgrid;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SendgridConfigTest {

  @Autowired
  private SendgridConfig sendgridConfig;

  @Test
  void shouldReturnYamlValues() {
    assertThat(sendgridConfig.getKey()).isEqualTo("foo");
    assertThat(sendgridConfig.getSender()).isEqualTo("foo");
  }

}