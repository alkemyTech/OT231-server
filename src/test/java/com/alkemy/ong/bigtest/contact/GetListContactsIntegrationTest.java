package com.alkemy.ong.bigtest.contact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GetListContactsIntegrationTest extends BigTest {

  @Test
  public void shouldReturnStatusOk() throws Exception {
    saveContact();
    saveContact();
    saveContact();
    saveContact();
    mockMvc.perform(get("/contacts")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnStatus400() throws Exception {
    saveContact();
    saveContact();
    saveContact();
    saveContact();
    mockMvc.perform(get("/contacts")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(400)))
        .andExpect(status().isBadRequest());
  }
}
