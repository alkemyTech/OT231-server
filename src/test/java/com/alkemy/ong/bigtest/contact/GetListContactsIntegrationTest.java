package com.alkemy.ong.bigtest.contact;

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
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN"))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", CoreMatchers.equalTo(
            "Access denied. Please, try to login again or contact your admin.")))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturnListContactsAndStatus200WhenRequestHasValidToken() throws Exception {
    saveContact();
    saveContact();
    saveContact();
    mockMvc.perform(get("/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.contacts[0].name", CoreMatchers.equalTo("Semper Evincere")))
        .andExpect(jsonPath("$.contacts[0].phone", CoreMatchers.equalTo("+540303456")))
        .andExpect(jsonPath("$.contacts[0].email", CoreMatchers.equalTo("semper@ong.com")))
        .andExpect(jsonPath("$.contacts[1].name", CoreMatchers.equalTo("Semper Evincere")))
        .andExpect(jsonPath("$.contacts[1].phone", CoreMatchers.equalTo("+540303456")))
        .andExpect(jsonPath("$.contacts[1].email", CoreMatchers.equalTo("semper@ong.com")))
        .andExpect(jsonPath("$.contacts[2].name", CoreMatchers.equalTo("Semper Evincere")))
        .andExpect(jsonPath("$.contacts[2].phone", CoreMatchers.equalTo("+540303456")))
        .andExpect(jsonPath("$.contacts[2].email", CoreMatchers.equalTo("semper@ong.com")))
        .andExpect(status().isOk());
  }

}
