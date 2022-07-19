package com.alkemy.ong.bigtest.news;

import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ListCommentsByNewsIntegrationTest extends BigTest {

  @Test
  public void shouldReturnCommentsWhenNewsHasCommentsAndRequestUserHasStandardRole() throws Exception {
    Long newsId = buildNewsWithComments();

    mockMvc.perform(get("/news/{id}/comments", String.valueOf(newsId))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.name", equalTo("My first News!!")))
        .andExpect(jsonPath("$.comments", hasSize(2)))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnNameWhenNewsHasNotCommentsAndRequestUserHasStandardRole() throws Exception {
    Long newsId = buildNewsWithoutComments();

    mockMvc.perform(get("/news/{id}/comments", String.valueOf(newsId))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.name", equalTo("My first News!!")))
        .andExpect(jsonPath("$.comments", emptyArray()))
        .andExpect(status().isOk());
  }

  private Long buildNewsWithComments() {
    NewsEntity newsEntity = saveNews();
    saveComment(newsEntity);
    saveComment(newsEntity);
    return newsEntity.getId();
  }

  private Long buildNewsWithoutComments() {
    return saveNews().getId();
  }
}
