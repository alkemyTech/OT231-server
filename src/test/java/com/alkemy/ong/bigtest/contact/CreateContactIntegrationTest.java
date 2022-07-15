package com.alkemy.ong.bigtest.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import com.alkemy.ong.infrastructure.rest.request.ContactRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateContactIntegrationTest extends BigTest {

  private static final String CREATE_CONTACT_URL = "/contacts";
  private static final String NAME = "Semper Evincere";
  private static final String PHONE = "+545885225885";
  private static final String MESSAGE = "please contact me";
  private static final String MAIL = "semper@gmail.com";

  @Test
  public void shouldCreateContactWhenRequestIsValid() throws Exception {
    /*ContactEntity contactEntity = saveContact();*/
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .content(createRequest(NAME, PHONE, MAIL, MESSAGE))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name", equalTo("Semper Evincere")))
        .andExpect(jsonPath("$.phone", equalTo("+545885225885")))
        .andExpect(jsonPath("$.email", equalTo("semper@gmail.com")))
        .andExpect(jsonPath("$.message", equalTo("please contact me")))
        .andExpect(status().isCreated());

    assertContactHasBeenCreated(MAIL);
  }

  @Test
  public void shouldReturn403WhenAuthTokenIsNotValid() throws Exception {
    /*ContactEntity contactEntity = saveContact();*/
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN")
            .content(createRequest(NAME, PHONE, MAIL, MESSAGE))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(
            "Access denied. Please, try to login again or contact your admin.")))
        .andExpect(status().isForbidden());
  }

  private void assertContactHasBeenCreated(String email) {
    Optional<ContactEntity> optionalContactEntity = Optional.of(contactRepository.findByEmail(email));
    assertTrue(true);
    assertThat(optionalContactEntity.get().getDeletedAt()).isNull();
    contactRepository.delete(optionalContactEntity.get());
  }

  private String createRequest(String name, String phone, String mail, String message)
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(ContactRequest.builder()
        .name(name)
        .phone(phone)
        .email(mail)
        .message(message)
        .build());
  }

}
