package com.alkemy.ong.bigtest.comment;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateCommentIntegrationTest extends BigTest {

  private static final String BODY = "My comment is very good";

  @Test
  public void shouldUpdateCommentWhenIsSameUser() throws Exception {
    Long randomCommentId = saveComment();

    mockMvc.perform(patch("/comments/" + randomCommentId)
            .content(createRequest())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.body", equalTo(BODY)))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldUpdateCommentWhenIsAdminUser() throws Exception {
    Long randomCommentId = saveComment();

    mockMvc.perform(patch("/comments/" + randomCommentId)
            .content(createRequest())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.body", equalTo(BODY)))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturn403WhenIsOtherUser() throws Exception {
    Long randomCommentId = saveComment();

    mockMvc.perform(patch("/comments/" + randomCommentId)
            .content(createRequest())
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardOtherUser())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo("Operation not permitted.")))
        .andExpect(status().isForbidden());
  }


  private String createRequest() throws JsonProcessingException {
    return objectMapper.writeValueAsString(CommentRequest.builder()
        .body(BODY)
        .build());
  }

}
