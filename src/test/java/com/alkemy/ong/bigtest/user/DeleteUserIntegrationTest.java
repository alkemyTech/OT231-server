package com.alkemy.ong.bigtest.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class DeleteUserIntegrationTest extends BigTest {


  private static final String DELETE_USER_URL = "/users/{id}";



  @Test
  public void shouldReturn403WhenMissingAuthToken() throws Exception {
    mockMvc.perform(delete(DELETE_USER_URL, "1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.statusCode", equalTo(403)))
            .andExpect(jsonPath("$.message",
                    equalTo("Access denied. Please, try to login again or contact your admin.")))
            .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturn404WhenUserDoesNotExist() throws Exception {
    mockMvc.perform(delete(DELETE_USER_URL, "100")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
                .andExpect(jsonPath("$.statusCode", equalTo(404)))
                .andExpect(jsonPath("$.message", equalTo("Record not found in database.")))
                .andExpect(jsonPath("$.moreInfo", hasSize(1)))
                .andExpect(jsonPath("$.moreInfo", hasItem("User not found.")))
                .andExpect(status().isNotFound());
  }

  private void assertUserHasBeenDeleted(Long id) {
    Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
    assertTrue(optionalUserEntity.isPresent());
    assertThat(optionalUserEntity.get().getSoftDelete()).isTrue();
  }

}
