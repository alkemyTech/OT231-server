package com.alkemy.ong.bigtest.contact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GetListContactsIntegrationTest extends BigTest {

  @Test
  public void shouldReturnStatusOk() throws Exception {
    saveContact();
    saveContact();

    mockMvc.perform(get("/contacts")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturn403WhenAuthTokenIsNotValid() throws Exception {
    mockMvc.perform(get("/contacts")
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN"))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(
            "Access denied. Please, try to login again or contact your admin.")))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnListContactsWhenRequestHasValidToken() throws Exception {
    saveContact();
    mockMvc.perform(get("/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.email", CoreMatchers.equalTo("semper@ong.com")))
        .andExpect(jsonPath("$.name", CoreMatchers.equalTo("Semper Evincere")))
        .andExpect(jsonPath("$.phone", CoreMatchers.equalTo("+540303456")))
        .andExpect(status().isOk());
  }

 /* @Test
  public void shouldReturnStatus400() throws Exception {
    saveContact();
    saveContact();
    saveContact();
    saveContact();
    mockMvc.perform(get("/contacts")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(status().isBadRequest());
  }*/
}
