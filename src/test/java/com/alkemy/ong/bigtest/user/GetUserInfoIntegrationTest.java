package com.alkemy.ong.bigtest.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GetUserInfoIntegrationTest extends BigTest {

  @Test
  public void shouldReturn403WhenAuthTokenIsNotValid() throws Exception {
    mockMvc.perform(get("/auth/me")
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnUserInfoWhenRequestHasValidToken() throws Exception {
    mockMvc.perform(get("/auth/me")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.email", equalTo("freedy@krueger.com")))
        .andExpect(jsonPath("$.firstName", equalTo("Freddy")))
        .andExpect(jsonPath("$.lastName", equalTo("Krueger")))
        .andExpect(jsonPath("$.photo", equalTo(null)))
        .andExpect(status().isOk());
  }
}
