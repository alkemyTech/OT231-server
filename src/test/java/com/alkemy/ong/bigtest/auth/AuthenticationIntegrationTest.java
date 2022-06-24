package com.alkemy.ong.bigtest.auth;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.rest.request.AuthenticationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.MediaType;

public class AuthenticationIntegrationTest extends BigTest {

  private static final String AUTH_LOGIN_URL = "/auth/login";

  @Test
  public void shouldReturnTokenWhenCredentialsAreValid() throws Exception {
    mockMvc.perform(post(AUTH_LOGIN_URL)
            .content(createRequest("freedy@krueger.com", "abcd1234"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.token", notNullValue()))
        .andExpect(jsonPath("$.firstName", equalTo("Freddy")))
        .andExpect(jsonPath("$.lastName", equalTo("Krueger")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnIsUnauthorizedStatusCodeWhenCredentialsAreInvalid() throws Exception {
    mockMvc.perform(post(AUTH_LOGIN_URL)
            .content(createRequest("freedy@krueger.com", "badPass"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(401)))
        .andExpect(jsonPath("$.message", equalTo("Invalid email or password.")))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Please, provide valid credentials.")))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void shouldReturnBadRequestStatusCodeWhenCredentialsHaveInvalidFormat() throws Exception {
    mockMvc.perform(post(AUTH_LOGIN_URL)
            .content(createRequest("wrongEmailFormat", "wrongPasswordFormat"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo("Invalid input data.")))
        .andExpect(jsonPath("$.moreInfo", hasSize(2)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Password must be between 6 and 8 characters.", "Email has invalid format.")))
        .andExpect(status().isBadRequest());
  }

  private String createRequest(String email, String password) throws JsonProcessingException {
    return objectMapper.writeValueAsString(AuthenticationRequest.builder()
        .email(email)
        .password(password)
        .build());
  }

}
