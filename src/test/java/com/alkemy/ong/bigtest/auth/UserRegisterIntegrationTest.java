package com.alkemy.ong.bigtest.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.MediaType;
import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public class UserRegisterIntegrationTest extends BigTest {
  
  private static final String REGISTER_URL = "/auth/register";
  private static final String USER_EMAIL = "michael@myers.com";
  private static final String USER_FIRSTNAME = "Michael";
  private static final String USER_LASTNAME = "Myers";
  private static final String USER_PASSWORD = "abcd1234";
  private static final String REGISTERED_USER_EMAIL = "freedy@krueger.com";
  
  @Test
  public void shouldCreateUserWhenRequestIsValid() throws Exception {
    mockMvc.perform(post(REGISTER_URL)
        .content(createRequest(USER_FIRSTNAME, USER_LASTNAME, USER_EMAIL, USER_PASSWORD))
        .contentType(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.firstName", equalTo("Michael")))
    .andExpect(jsonPath("$.lastName", equalTo("Myers")))
    .andExpect(jsonPath("$.email", equalTo("michael@myers.com")))
    .andExpect(jsonPath("$.token", notNullValue()))
    .andExpect(status().isCreated());
    
    assertUserHasBeenCreated(USER_EMAIL);
  }
  
  @Test
  public void shouldReturn400WhenEmailIsAlreadyBeingUsed() throws Exception {
    mockMvc.perform(post(REGISTER_URL)
            .content(
                createRequest(USER_FIRSTNAME, USER_LASTNAME, REGISTERED_USER_EMAIL, USER_PASSWORD))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo("Invalid input data.")))
        .andExpect(jsonPath("$.moreInfo",
            hasItem("Email address: " + REGISTERED_USER_EMAIL + " is already being used")))
        .andExpect(status().isBadRequest());
  }
  
  @Test
  public void shouldReturn400WhenCredentialsHaveInvalidFormat() throws Exception {
    mockMvc.perform(post(REGISTER_URL)
            .content(
                createRequest(USER_FIRSTNAME, USER_LASTNAME, "wrongEmailFormat", "wrongPasswordFormat"))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(jsonPath("$.message", equalTo("Invalid input data.")))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Password must be between 6 and 8 characters", "Email should be valid")))
        .andExpect(status().isBadRequest());
  }


  private void assertUserHasBeenCreated(String email) {
    Optional<UserEntity> optionalUserEntity = Optional.of(userRepository.findByEmail(email));
    assertTrue(optionalUserEntity.isPresent());
    assertThat(optionalUserEntity.get().getSoftDelete()).isFalse();
  }

  private String createRequest(String firstName, String lastName, String email, String password)
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(UserRegisterRequest.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .password(password)
        .build());
  }

}
