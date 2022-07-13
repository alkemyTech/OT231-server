package com.alkemy.ong.bigtest.slides;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.SlideEntity;

public class GetSlideIntegrationTest extends BigTest {
  
  @Test
  public void shouldReturnSlideWhenRequestHasValidToken() throws Exception {
    SlideEntity slide = saveSlide();

    mockMvc.perform(get("/slides/{id}", String.valueOf(slide.getId()))
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
      .andExpect(jsonPath("$.imageUrl", notNullValue()))
      .andExpect(jsonPath("$.order", notNullValue()))
      .andExpect(status().isOk());

    slideRepository.deleteAll();
  }

  @Test
  public void shouldReturn404WhenSlideDoesNotExist() throws Exception {
    mockMvc.perform(get("/slides/1")
        .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
      .andExpect(jsonPath("$.statusCode", equalTo(404)))
      .andExpect(jsonPath("$.message", equalTo("Record not found in database.")))
      .andExpect(jsonPath("$.moreInfo", hasSize(1)))
      .andExpect(jsonPath("$.moreInfo", hasItem("Slide not found.")))
      .andExpect(status().isNotFound());
  }

}
