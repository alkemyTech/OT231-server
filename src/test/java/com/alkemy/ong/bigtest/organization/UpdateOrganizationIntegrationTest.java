package com.alkemy.ong.bigtest.organization;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import com.alkemy.ong.infrastructure.rest.request.UpdateOrganizationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class UpdateOrganizationIntegrationTest extends BigTest {

  private static final String NAME = "Somos Mas";
  private static final String ADDRESS = "Street 1234";
  private static final String PHONE = "+54123456";
  private static final String EMAIL = "somos@mas.com";
  private static final String IMAGE = "https://s3.com/organization.jpg";
  private static final String WELCOMETEXT = "Welcome to Somos Mas the best ONG ever!";
  private static final String NOTVALIDWELCOMETEXT = "Lorem ipsum, dolor sit amet " +
          "consectetur adipisicing elit. Vero incidunt numquam optio? Laudantium, " +
          "quos eligendi! Adipisci ratione recusandae ad voluptatem earum ipsum quae, " +
          "iste obcaecati placeat rerum, repudiandae repellat molestiae dolore nemo deleniti " +
          "neque harum? Dicta ea fuga nisi ullam recusandae numquam laborum placeat! " +
          "Expedita magni dicta debitis ea praesentium repellendus delectus.";

  @Test
  public void shouldUpdateOrganizationWhenUserHasAdminRole() throws Exception {
    Long randomOrganizationId = saveOrganization();
    mockMvc.perform(patch("/organization/public", String.valueOf(randomOrganizationId))
            .content(createRequest())
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.name", equalTo(NAME)))
        .andExpect(jsonPath("$.address", equalTo(ADDRESS)))
        .andExpect(jsonPath("$.phone", equalTo(PHONE)))
        .andExpect(jsonPath("$.email", equalTo(EMAIL)))
        .andExpect(status().isOk());
    assertOrganizationHasBeenUpdated(randomOrganizationId);
  }

  @Test
  public void shouldReturn403ErrorWhenUserHasStandardUserRole() throws Exception {
    mockMvc.perform(patch("/organization/public")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
            .andExpect(jsonPath("$.statusCode", equalTo(403)))
            .andExpect(jsonPath("$.message", equalTo("Access denied. Please, " +
                    "try to login again or contact your admin.")))
            .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturn400WhenEmailIsNotValid() throws Exception {
    mockMvc.perform(patch("/organization/public")
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON)
            .content(createRequest(NAME, ADDRESS, PHONE, "somos mas@ ong.com", IMAGE, WELCOMETEXT)))
            .andExpect(jsonPath("$.statusCode", equalTo(400)))
            .andExpect(jsonPath("$.message", equalTo("Invalid input data.")))
            .andExpect(jsonPath("$.moreInfo",
                    hasItems("Email should be valid")))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturn400WhenImageIsNotValid() throws Exception {
    mockMvc.perform(patch("/organization/public")
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createRequest(NAME, ADDRESS, PHONE, EMAIL, "https://s3. com/image .jpg", WELCOMETEXT)))
            .andExpect(jsonPath("$.statusCode", equalTo(400)))
            .andExpect(jsonPath("$.message", equalTo("Invalid input data.")))
            .andExpect(jsonPath("$.moreInfo",
                    hasItems("Image can only contain alphanumeric characters without whitespaces")))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturn400WhenNameIsNotValid() throws Exception {
    mockMvc.perform(patch("/organization/public")
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createRequest("Lola!!!", ADDRESS, PHONE, EMAIL, IMAGE, WELCOMETEXT)))
            .andExpect(jsonPath("$.statusCode", equalTo(400)))
            .andExpect(jsonPath("$.message", equalTo("Invalid input data.")))
            .andExpect(jsonPath("$.moreInfo",
                    hasItems("Name can only contain alphanumeric characters and whitespaces")))
            .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldReturn400WhenWelcomeTextIsNotValid() throws Exception {
    mockMvc.perform(patch("/organization/public")
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createRequest(NAME, ADDRESS, PHONE, EMAIL, IMAGE, NOTVALIDWELCOMETEXT)))
            .andExpect(jsonPath("$.statusCode", equalTo(400)))
            .andExpect(jsonPath("$.message", equalTo("Invalid input data.")))
            .andExpect(jsonPath("$.moreInfo",
                    hasItems("Welcome text must have a maximum of 50 alphanumeric characters")))
            .andExpect(status().isBadRequest());
  }

  private String createRequest(String name, String address, String phone, String email, String image, String welcomeText)
          throws JsonProcessingException {
    return objectMapper.writeValueAsString(UpdateOrganizationRequest.builder()
            .name(name)
            .address(address)
            .phone(phone)
            .email(email)
            .image(image)
            .welcomeText(welcomeText)
            .build());
  }

  private String createRequest() throws JsonProcessingException {
    return objectMapper.writeValueAsString(UpdateOrganizationRequest.builder()
            .name(NAME)
            .address(ADDRESS)
            .phone(PHONE)
            .email(EMAIL)
            .image(IMAGE)
            .welcomeText(WELCOMETEXT)
            .build());
  }

  private void assertOrganizationHasBeenUpdated(Long id) {
    Optional<OrganizationEntity> organizationEntity = organizationRepository.findById(id);
    assertTrue(organizationEntity.isPresent());
    assertEquals(NAME, organizationEntity.get().getName());
    assertEquals(ADDRESS, organizationEntity.get().getAddress());
    assertEquals(PHONE, organizationEntity.get().getPhone());
    assertEquals(EMAIL, organizationEntity.get().getEmail());
    assertEquals(IMAGE, organizationEntity.get().getImage());
    assertEquals(WELCOMETEXT, organizationEntity.get().getWelcomeText());
    organizationRepository.delete(organizationEntity.get());
  }
}
