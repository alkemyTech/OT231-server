package com.alkemy.ong.bigtest.comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateCommentIntegrationTest extends BigTest {

  private static final String BODY = "My comment";

  @Test
  public void shouldCreateCommentWhenRequestUserHasStandardRole() throws Exception {
    String response = mockMvc.perform(post("/comments")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest()))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.body", equalTo(BODY)))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    Integer commentId = JsonPath.read(response, "$.id");
    assertCommentHasBeenCreated(Long.valueOf(commentId));
  }

  private void assertCommentHasBeenCreated(Long id) {
    Optional<NewsEntity> optionalNewsEntity = newsRepository.findById(id);
    assertTrue(optionalNewsEntity.isPresent());
    assertThat(optionalNewsEntity.get().getSoftDelete()).isFalse();
  }

  private String createRequest() throws JsonProcessingException {
    return objectMapper.writeValueAsString(CommentRequest.builder()
        .body(BODY)
        .newsId(saveNews().getId())
        .userId(getRandomUserId())
        .build());
  }

}
