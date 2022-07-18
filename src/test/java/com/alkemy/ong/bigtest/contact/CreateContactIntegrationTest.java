package com.alkemy.ong.bigtest.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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

public class CreateContactIntegrationTest extends BigTest {

  private static final String CREATE_CONTACT_URL = "/contacts";
  private static final String NAME = "Semper Evincere";
  private static final String PHONE = "+545885225885";
  private static final String MESSAGE = "please contact me";
  private static final String MAIL = "semper@gmail.com";
  private static final String INVALID_NAME = "Semper78 Evincere";
  private static final String INVALID_MAIL = "semper@ gmail.com";
  private static final String INVALID_MESSAGE = "Lorem ipsum dolor sit amet, consectetur "
      + "adipiscing elit. Aenean id eleifend lacus. Duis sollicitudin euismod nibh, vitae "
      + "fermentum augue consequat est.";
  private static final String ACCESS_DENIED = "Access denied. Please, try to login again or "
      + "contact your admin.";

  @Test
  public void shouldCreateContactWhenRequestIsValid() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .content(createRequest())
            .contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.phone", equalTo(PHONE)))
        .andExpect(jsonPath("$.email", equalTo(MAIL)))
        .andExpect(jsonPath("$.message", equalTo(MESSAGE)))
        .andExpect(status().isCreated());

    assertContactHasBeenCreated();
  }

  @Test
  public void shouldReturn403WhenAuthTokenIsNotValid() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN")
            .content(createRequest())
            .contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(
            ACCESS_DENIED)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void fieldsRequestShouldValidate() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createRequest())))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void fieldNameRequestNotValidate() throws Exception {
      mockMvc.perform(post(CREATE_CONTACT_URL)
      .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
      .contentType(APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(createBadRequestFieldName())))
          .andExpect(status().isBadRequest());
  }

  @Test
  public void fieldMailRequestNotValidate() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createBadRequestFieldMail())))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void fieldMessageRequestNotValidate() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createBadRequestFieldMessageMaxCharacters())))
        .andExpect(status().isBadRequest());
  }

  private String createBadRequestFieldMessageMaxCharacters()
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(ContactRequest.builder()
        .name(CreateContactIntegrationTest.NAME)
        .phone(CreateContactIntegrationTest.MAIL)
        .message(CreateContactIntegrationTest.INVALID_MESSAGE)
        .build());
  }

  private String createBadRequestFieldName()
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(ContactRequest.builder()
        .name(CreateContactIntegrationTest.INVALID_NAME)
        .email(CreateContactIntegrationTest.MAIL)
        .message(CreateContactIntegrationTest.MESSAGE)
        .build());
  }

  private void assertContactHasBeenCreated() {
    Optional<ContactEntity> optionalContactEntity = Optional.of(contactRepository.findByEmail(
        CreateContactIntegrationTest.MAIL));
    assertTrue(true);
    assertThat(optionalContactEntity.get().getDeletedAt()).isNull();
    assertThat(optionalContactEntity.get().getName()).isEqualTo(NAME);
    assertThat(optionalContactEntity.get().getEmail()).isEqualTo(MAIL);
    assertThat(optionalContactEntity.get().getMessage()).isEqualTo(MESSAGE);
    assertThat(optionalContactEntity.get().getPhone()).isEqualTo(PHONE);
    contactRepository.delete(optionalContactEntity.get());
  }

  private String createRequest()
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(ContactRequest.builder()
        .name(CreateContactIntegrationTest.NAME)
        .phone(CreateContactIntegrationTest.PHONE)
        .email(CreateContactIntegrationTest.MAIL)
        .message(CreateContactIntegrationTest.MESSAGE)
        .build());
  }

  private String createBadRequestFieldMail()
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(ContactRequest.builder()
        .name(CreateContactIntegrationTest.NAME)
        .phone(CreateContactIntegrationTest.INVALID_MAIL)
        .message(CreateContactIntegrationTest.MESSAGE)
        .build());
  }

}
