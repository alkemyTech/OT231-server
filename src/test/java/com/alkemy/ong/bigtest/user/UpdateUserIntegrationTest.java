package com.alkemy.ong.bigtest.user;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.rest.request.UpdateUserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public class UpdateUserIntegrationTest extends BigTest {
  
  private static final String USER_EMAIL = "freedy@krueger.com";
  private static final Long NON_EXISTING_USER_ID = 999L;
  
  @Test
  public void shouldUpdateExistingUserWhenAuthenticated() throws Exception {
    Long userId = userRepository.findByEmail(USER_EMAIL).getId();
    
    mockMvc.perform(put("/users/" + userId)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
        .contentType(MediaType.APPLICATION_JSON)
        .content(createRequest
            ("Freedy", "K", "fkrueger@alkemy.com", "password", "http://aws.s3.com/profile.jpg")))
    .andExpect(jsonPath("$.firstName", equalTo("Freedy")))
    .andExpect(jsonPath("$.lastName", equalTo("K")))
    .andExpect(jsonPath("$.email", equalTo("fkrueger@alkemy.com")))
    .andExpect(jsonPath("$.photo", equalTo("http://aws.s3.com/profile.jpg")))
    .andExpect(status().isOk());
  }
  
  @Test
  public void shouldReturn404WhenUserDoesNotExists() throws Exception {
    mockMvc.perform(put("/users/" + NON_EXISTING_USER_ID)
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
        .contentType(MediaType.APPLICATION_JSON)
        .content(createRequest("Michael", "Myers", "michael@myers.com", "password", null)))
    .andExpect(status().isNotFound());
  }
  
  @Test
  public void shouldReturn403WhenUserIsNotAuthenticated() throws Exception {
    Long userId = userRepository.findByEmail(USER_EMAIL).getId();

    mockMvc.perform(put("/users/" + userId)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.statusCode", equalTo(403)))
      .andExpect(jsonPath("$.message",
          equalTo("Access denied. Please, try to login again or contact your admin.")))
      .andExpect(status().isForbidden());
  }
  
  private String createRequest(String firstName, String lastName, String email, 
      String password, String photo)
      throws JsonProcessingException {
        return objectMapper.writeValueAsString(UpdateUserRequest.builder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .password(password)
            .photo(photo)
            .build());
  }

}
