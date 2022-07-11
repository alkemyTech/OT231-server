package com.alkemy.ong.bigtest.organization;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import org.junit.Test;
import org.springframework.http.MediaType;

public class GetOrganizationIntegrationTest extends BigTest {

  @Test
  public void shouldReturnOrganization() throws Exception {
    saveOrganization();
    mockMvc.perform(get("/organization/public")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name", equalTo("Somos Mas")))
        .andExpect(jsonPath("$.image", equalTo("https://s3.com/logo.jpg/")))
        .andExpect(jsonPath("$.address", equalTo("Elm Street 3")))
        .andExpect(jsonPath("$.phone", equalTo("+540303456")))
        .andExpect(
            jsonPath("$.socialMedia.facebookUrl", equalTo("https://www.facebook.com/Somos_Mas/")))
        .andExpect(
            jsonPath("$.socialMedia.instagramUrl", equalTo("https://www.instagram.com/SomosMas/")))
        .andExpect(jsonPath("$.socialMedia.linkedInUrl",
            equalTo("https://www.linkedin.com/in/Somos-Mas/")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturn404WhenOrganizationDoesNotExist() throws Exception {
    mockMvc.perform(get("/organization/public")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo("Record not found in database.")))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect((jsonPath("$.moreInfo", contains("Organization not found."))))
        .andExpect(status().isNotFound());
  }

}
