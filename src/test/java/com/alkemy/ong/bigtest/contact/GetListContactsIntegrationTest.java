package com.alkemy.ong.bigtest.contact;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import java.sql.Date;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GetListContactsIntegrationTest extends BigTest {

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
    saveContact(null,
        "semper@gmail.com",
        "message for contact I",
        "Semper Evincere","+54448555555");
    saveContact(null,
        "semper@gmail.com",
        "message for contact II",
        "Semper Evincere","+54448555555");
    saveContact(null,
        "foo@gmail.com",
        "message for contact",
        "fooName","+5444930145555");

    mockMvc.perform(get("/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.contacts[0].name", equalTo("Semper Evincere")))
        .andExpect(jsonPath("$.contacts[0].phone", equalTo("+54448555555")))
        .andExpect(jsonPath("$.contacts[0].email", equalTo("semper@gmail.com")))
        .andExpect(jsonPath("$.contacts[1].name", equalTo("fooName")))
        .andExpect(jsonPath("$.contacts[1].phone", equalTo("+5444930145555")))
        .andExpect(jsonPath("$.contacts[1].email", equalTo("foo@gmail.com")))
        .andExpect(jsonPath("$.contacts", hasSize(2)))
        .andExpect(status().isOk());
<<<<<<< HEAD
    contactRepository.deleteAll();
=======
>>>>>>> main
  }

  @Test
  public void shouldReturnEmptyContactListAndStatus200WhenNoRecordsExist() throws Exception {
    mockMvc.perform(get("/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.contacts").isEmpty())
        .andExpect(status().isOk());
  }

  @Test
<<<<<<< HEAD
  public void shouldReturnEmptyContactListAndStatus200WhenContacsIsNotActive() throws Exception {
=======
  public void shouldReturnEmptyContactListAndStatus200WhenContactsIsNotActive() throws Exception {
>>>>>>> main
    saveContact(new Date(2020,10,12),
        "semper@gmail.com",
        "message for contact",
        "Semper Evincere","+54448555555");

    mockMvc.perform(get("/contacts")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.contacts").isEmpty())
        .andExpect(status().isOk());
<<<<<<< HEAD
    contactRepository.deleteAll();
=======
>>>>>>> main
  }
}
