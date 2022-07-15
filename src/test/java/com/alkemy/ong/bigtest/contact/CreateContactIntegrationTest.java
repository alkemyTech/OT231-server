package com.alkemy.ong.bigtest.contact;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateContactIntegrationTest extends BigTest {

  @Test
  public void shouldCreateContactWhenRequestUserHasAdminRole() throws Exception {
    saveContact(null,
        "semper@gmail.com",
        "message for contact I",
        "Semper Evincere","+54448555555");

    mockMvc.perform(post("/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
  }

}
