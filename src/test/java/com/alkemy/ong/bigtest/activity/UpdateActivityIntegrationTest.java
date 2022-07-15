package com.alkemy.ong.bigtest.activity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.rest.response.ActivityResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateActivityIntegrationTest extends BigTest{

  private static final String NAME= "Small Activity";

  private static final String INVALID_NAME= "Big Activity and Example of error with more characters";

  private static final String CONTENT= "Content examples again";

  private static final String IMAGE= "image.jotapeje";

  private static final String INVALID_IMAGE= "i m a g e.p e n e j e";

  private static final Long NON_EXISTING_ACTIVITY_ID = 999L;

  @Test
  public void shouldUpdateActivityWhenRequestUserHasAdminRole() throws Exception {
    ActivityEntity activityId = saveActivity();

    mockMvc.perform(put("/activities/{id}", String.valueOf(activityId.getId()))
            .content(createRequest())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.content", equalTo(CONTENT)))
        .andExpect(jsonPath("$.image", equalTo(IMAGE)))
        .andExpect(status().isOk());

    assertActivityHasBeenUpdated(activityId.getId());
  }


  @Test
  public void shouldReturn400WhenNameIsNotValid() throws Exception {
    ActivityEntity activityId = saveActivity();
    mockMvc.perform(put("/activities/{id}", String.valueOf(activityId.getId()))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(INVALID_NAME, CONTENT, IMAGE)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Name must have a maximum of 50 characters")))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturn404WhenActivityDoesNotExists() throws Exception {
    mockMvc.perform(put("/activities/" + NON_EXISTING_ACTIVITY_ID)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, CONTENT, IMAGE)))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturn403WhenUserIsNotAuthenticated() throws Exception {
    ActivityEntity activityId = saveActivity();
    mockMvc.perform(put("/activities/{id}", String.valueOf(activityId.getId()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", IsEqual.equalTo(403)))
        .andExpect(jsonPath("$.message",
            IsEqual.equalTo("Access denied. Please, try to login again or contact your admin.")))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturn400WhenImageIsNotValid() throws Exception {
    ActivityEntity activityId = saveActivity();
    mockMvc.perform(put("/activities/{id}", String.valueOf(activityId.getId()))
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, CONTENT, INVALID_IMAGE)))
        .andExpect(jsonPath("$.moreInfo",
            hasItems("Image can only contain alphanumerical with no whitespaces.")))
        .andExpect(status().isBadRequest());
  }

  private String createRequest(String name, String content, String image)
      throws JsonProcessingException {
    return objectMapper.writeValueAsString(ActivityResponse.builder()
        .name(name)
        .content(content)
        .image(image)
        .build());
  }

  private String createRequest() throws JsonProcessingException {
    return createRequest(NAME, CONTENT, IMAGE);
  }

  private void assertActivityHasBeenUpdated(Long id) {
    Optional<ActivityEntity> activityEntity = activityRepository.findById(id);
    assertTrue(activityEntity.isPresent());
    assertEquals(NAME, activityEntity.get().getName());
    assertEquals(CONTENT, activityEntity.get().getContent());
    assertEquals(IMAGE, activityEntity.get().getImage());
    activityRepository.delete(activityEntity.get());
  }
}