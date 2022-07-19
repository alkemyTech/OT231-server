package com.alkemy.ong.bigtest.user;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.spring.IUserSpringRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


public class GetUserIntegrationTest extends BigTest {

  @Autowired
  private IUserSpringRepository userSpringRepository;

  @Test
  public void shouldReturnUsersListWhenAdminIsAuthenticated() throws Exception{

    mockMvc.perform(get("/users")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON))

        .andExpect(jsonPath("$.users", hasSize(3)))
        .andExpect(jsonPath("$.users[0].email", equalTo("jason@voorhees.com")))
        .andExpect(jsonPath("$.users[1].email", equalTo("freedy@krueger.com")))
        .andExpect(jsonPath("$.users[2].email", equalTo("john@connors.com")))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldNotReturnUserDeletedInUsersList() throws Exception{
    createdUserWhitSoftDeleteTrue();
    mockMvc.perform(get("/users")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.users", hasSize(3)))
            .andExpect(jsonPath("$.users[0].email", equalTo("jason@voorhees.com")))
            .andExpect(jsonPath("$.users[1].email", equalTo("freedy@krueger.com")))
            .andExpect(jsonPath("$.users[2].email", equalTo("john@connors.com")))
            .andExpect(status().isOk());

  }

  private void createdUserWhitSoftDeleteTrue() {
    UserEntity user = UserEntity.builder()
            .firstName("pedro")
            .lastName("paez")
            .email("mdsd@gmail.com")
            .password("abcd1234")
            .softDelete(true)
            .build();
    userSpringRepository.save(user);

  }

  @Test
  public void shouldReturn403WhenAuthTokenIsNotAdmin() throws Exception {
    mockMvc.perform(get("/users")
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", CoreMatchers.equalTo(403)))
        .andExpect(jsonPath("$.message", equalTo(
          "Access denied. Please, try to login again or contact your admin.")))
        .andExpect(status().isForbidden());
  }

}
