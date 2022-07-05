package com.alkemy.ong.bigtest.organization;

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

  private String createRequest() throws JsonProcessingException {
    return objectMapper.writeValueAsString(UpdateOrganizationRequest.builder()
        .name(NAME)
        .address(ADDRESS)
        .phone(PHONE)
        .email(EMAIL)
        .build());
  }

  private void assertOrganizationHasBeenUpdated(Long id) {
    Optional<OrganizationEntity> organizationEntity = organizationRepository.findById(id);
    assertTrue(organizationEntity.isPresent());
    assertEquals(NAME, organizationEntity.get().getName());
    assertEquals(ADDRESS, organizationEntity.get().getAddress());
    assertEquals(PHONE, organizationEntity.get().getPhone());
    assertEquals(EMAIL, organizationEntity.get().getEmail());

    organizationRepository.delete(organizationEntity.get());
  }
}
