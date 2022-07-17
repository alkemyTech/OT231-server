package com.alkemy.ong.bigtest.comment;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteCommentIntegrationTest extends BigTest {

  private static final String DELETE_COMMENTS_URL = "/comments/{id}";

  @Test
  public void shouldDeleteCommentWhenRequestUserIsCommentCreator() throws Exception {
    Long commentId = saveComment();

    mockMvc.perform(delete(DELETE_COMMENTS_URL, commentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
            .andExpect(status().isNoContent());

    assertCommentHasBeenDeleted(commentId);
  }

  @Test
  public void shouldDeleteCommentWhenRequestUserIsAdminUser() throws Exception {
    Long commentId = saveComment();

    mockMvc.perform(delete(DELETE_COMMENTS_URL, commentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
            .andExpect(status().isNoContent());

    assertCommentHasBeenDeleted(commentId);
  }

  @Test
  public void shouldReturn403WhenRequestUserIsOtherUser() throws Exception {
    Long commentId = saveComment();

    mockMvc.perform(delete(DELETE_COMMENTS_URL, commentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardOtherUser()))
            .andExpect(jsonPath("$.statusCode", IsEqual.equalTo(403)))
            .andExpect(jsonPath("$.message",
                    IsEqual.equalTo("Operation not permitted.")))
            .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturn403WhenMissingAuthToken() throws Exception {
    mockMvc.perform(delete(DELETE_COMMENTS_URL, "1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode", equalTo(403)))
            .andExpect(jsonPath("$.message",
                    equalTo("Access denied. Please, try to login again or contact your admin.")))
            .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturn404WhenCommentDoesNotExist() throws Exception {
    mockMvc.perform(delete(DELETE_COMMENTS_URL, "1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
            .andExpect(jsonPath("$.statusCode", equalTo(404)))
            .andExpect(jsonPath("$.message", equalTo("Record not found in database.")))
            .andExpect(jsonPath("$.moreInfo", hasSize(1)))
            .andExpect(jsonPath("$.moreInfo", hasItem("Comment not found.")))
            .andExpect(status().isNotFound());
  }


  private void assertCommentHasBeenDeleted(Long id) {
    Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(id);
    assertTrue(optionalCommentEntity.isPresent());
    assertThat(optionalCommentEntity.get().getSoftDelete()).isTrue();
  }

}
