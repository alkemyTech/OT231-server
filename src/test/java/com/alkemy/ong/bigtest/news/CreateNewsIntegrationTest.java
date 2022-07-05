package com.alkemy.ong.bigtest.news;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.rest.response.NewsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateNewsIntegrationTest extends BigTest {

  private static final String NAME = "These are incredible News!!";
  private static final String TEXT = "News content.";
  private static final String IMAGE = "https://s3.com/news.jpg";
  private static final String INVALID_NAME = "This name contains more than permitted characters!!";
  private NewsResponse response;

  @Test
  public void shouldReturn403WhenAuthTokenIsInvalid() throws Exception {
    mockMvc.perform(post("/news")
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldCreateNewsWhenRequestUserHasAdminRole() throws Exception {
    mockMvc.perform(post("/news")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, TEXT, IMAGE)))
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.name", equalTo("These are incredible News!!")))
        .andExpect(jsonPath("$.text", equalTo("News content.")))
        .andExpect(jsonPath("$.image", equalTo("https://s3.com/news.jpg")))
        .andExpect(status().isCreated())
        .andDo(MvcResult -> {
          String json = MvcResult.getResponse().getContentAsString();
          response = objectMapper.readValue(json, NewsResponse.class);
        });

    assertNewsHasBeenCreated(response.getId());
  }

  @Test
  public void shouldReturn400WhenNameIsNotValid() throws Exception {
    mockMvc.perform(post("/news")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(INVALID_NAME, TEXT, IMAGE)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Name should be 50 characters or less.")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturn400WhenImageIsNotValid() throws Exception {
    mockMvc.perform(post("/news")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, TEXT, "https://s3. com/image .jpg")))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Image can only contain alphanumerical with no whitespaces.")))
        .andExpect(status().isBadRequest());
  }

  private void assertNewsHasBeenCreated(Long id) {
    Optional<NewsEntity> optionalNewsEntity = newsRepository.findById(id);
    assertTrue(optionalNewsEntity.isPresent());
    assertThat(optionalNewsEntity.get().getSoftDelete()).isFalse();
  }

  private String createRequest(String name, String text, String image)
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(News.builder()
        .name(name)
        .text(text)
        .image(image)
        .build());
  }

}
