package com.alkemy.ong.bigtest.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import com.alkemy.ong.infrastructure.rest.request.ContactRequest;
import com.alkemy.ong.infrastructure.rest.response.ContactResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateContactIntegrationTest extends BigTest {

  private static final String CREATE_CONTACT_URL = "/contacts";

  private static final String NAME = "Semper Evincere";
  private static final String MESSAGE = "please contact me";
  private static final String MAIL = "semper@gmail.com";
  private static final String INVALID_NAME = "Semper78 Evincere";
  private static final String INVALID_MAIL = "semper@ gmail.com";
  private static final String INVALID_MESSAGE = "Lorem ipsum dolor sit amet, consectetur "
      + "adipiscing elit. Aenean id eleifend lacus. Duis sollicitudin euismod nibh, vitae "
      + "fermentum augue consequat est.";
  private static final String ACCESS_DENIED = "Access denied. Please, try to login again or "
      + "contact your admin.";
  private ContactResponse contactResponse;

  @Test
  public void shouldCreateContactWhenRequestIsValid() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .content(createRequest(NAME, MAIL, MESSAGE))
            .contentType(APPLICATION_JSON))
        .andExpectAll().andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.email", equalTo(MAIL)))
        .andExpect(jsonPath("$.message", equalTo(MESSAGE)))
        .andExpect(status().isCreated())
        .andDo(MvcResult -> {
          String json = MvcResult.getResponse().getContentAsString();
          contactResponse = objectMapper.readValue(json, ContactResponse.class);
        });

    assertContactHasBeenCreated(contactResponse.getEmail());
  }

  @Test
  public void shouldReturn403WhenAuthTokenIsNotValid() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN")
            .content(createRequest(NAME, MAIL, MESSAGE))
            .contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(ACCESS_DENIED)))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnStatusCode400WhenNameIsNotValid() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(INVALID_NAME, MAIL, MESSAGE)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Name can only contain letters and whitespaces")))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnStatusCode400WhenEmailIsNotValid() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, INVALID_MAIL, MESSAGE)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Email should be valid")))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturnStatusCode400WhenMessageIsNotValid() throws Exception {
    mockMvc.perform(post(CREATE_CONTACT_URL)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, MAIL, INVALID_MESSAGE)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Message must have a maximum of 150 characters")))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(status().isBadRequest());
  }

  private String createRequest(String name, String email, String message)
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(ContactRequest.builder()
        .name(name)
        .email(email)
        .message(message)
        .build());
  }

  private void assertContactHasBeenCreated(String contactEmail) {
    ContactEntity contactEntity = contactRepository.findByEmail(contactEmail);
    assertNotNull(contactEntity);
    assertThat(contactEntity.getDeletedAt()).isNull();
  }

}
