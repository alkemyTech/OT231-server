package com.alkemy.ong.bigtest.activities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.rest.response.ActivityResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CreateActivityIntegrationTest extends BigTest{

  private static final String NAME= "Big Activity";

  private static final String INVALID_NAME= "Big Activity and Example of error with more characters";

  private static final String CONTENT= "Content example";

  private static final String IMAGE= "image.peneje";

  private static final String INVALID_IMAGE= "i m a g e.p e n e j e";


  @Test
  public void shouldReturn403WhenAuthTokenIsInvalid() throws Exception {
    mockMvc.perform(post("/activities")
            .header(HttpHeaders.AUTHORIZATION, "INVALID_TOKEN"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldCreateActivityWhenRequestUserHasAdminRole() throws Exception {
    mockMvc.perform(post("/activities")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, CONTENT, IMAGE)))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

    assertActivityHasBeenCreated(NAME);
  }


  @Test
  public void shouldReturn400WhenNameIsNotValid() throws Exception {
    mockMvc.perform(post("/activities")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(INVALID_NAME, CONTENT, IMAGE)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Name must have a maximum of 50 characters")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturn400WhenImageIsNotValid() throws Exception {
    mockMvc.perform(post("/activities")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, CONTENT, INVALID_IMAGE)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Image can only contain alphanumerical with no whitespaces.")))
        .andExpect(status().isBadRequest());
  }
  private void assertActivityHasBeenCreated(String name) {
    Optional<ActivityEntity> optionalActivityEntity = Optional.ofNullable(
        activityRepository.findByName(name));
    assertTrue(optionalActivityEntity.isPresent());
    assertThat(optionalActivityEntity.get().getSoftDelete()).isFalse();
  }
  private String createRequest(String name, String content, String image)
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(ActivityResponse.builder()
        .name(name)
        .content(content)
        .image(image)
        .build());
  }

}