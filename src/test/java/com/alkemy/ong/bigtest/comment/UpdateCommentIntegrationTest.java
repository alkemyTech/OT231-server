package com.alkemy.ong.bigtest.comment;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateCommentIntegrationTest extends BigTest {

  private static final String BODY = "My comment is very good";

  @Test
  public void shouldUpdateCommentWhenIsSameUser() throws Exception {
    Long randomCommentId = saveComment();

    mockMvc.perform(patch("/comments/" + randomCommentId)
                    .content(createRequest())
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
            .andExpect(jsonPath("$.body", IsEqual.equalTo(BODY)))
            .andExpect(status().isOk());

    assertCommentHasBeenUpdated(randomCommentId);
  }

  @Test
  public void shouldUpdateCommentWhenIsAdminUser() throws Exception {
    Long randomCommentId = saveComment();

    mockMvc.perform(patch("/comments/" + randomCommentId)
                    .content(createRequest())
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
            .andExpect(jsonPath("$.body", IsEqual.equalTo(BODY)))
            .andExpect(status().isOk());

    assertCommentHasBeenUpdated(randomCommentId);
  }

  @Test
  public void shouldReturn403WhenIsOtherUser() throws Exception {
    Long randomCommentId = saveComment();

    mockMvc.perform(patch("/comments/" + randomCommentId)
                    .content(createRequest())
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardOtherUser())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode", IsEqual.equalTo(403)))
            .andExpect(jsonPath("$.message",
                    IsEqual.equalTo("Operation not permitted.")))
            .andExpect(status().isForbidden());
  }


  private String createRequest() throws JsonProcessingException {
    return objectMapper.writeValueAsString(CommentRequest.builder()
        .body(BODY)
        .build());
  }

  private void assertCommentHasBeenUpdated(Long id) {
    Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(id);
  }

}
