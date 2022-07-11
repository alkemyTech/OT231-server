package com.alkemy.ong.bigtest.member;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.bigtest.BigTest;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import java.util.Optional;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class DeleteMemberIntegrationTest extends BigTest {

  @Test
  public void shouldDeleteMemberWhenUserHasAdminRole() throws Exception {
    MemberEntity randomMember = getRandomMember();

    mockMvc.perform(delete("/members/{id}", String.valueOf(randomMember.getId()))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(status().isNoContent());

    assertMemberHasBeenDeleted(randomMember.getId());
  }

  @Test
  public void shouldReturnF403WhenUserHasStandardRole() throws Exception {
    MemberEntity randomMember = getRandomMember();

    mockMvc.perform(delete("/members/{id}", String.valueOf(randomMember.getId()))
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForStandardUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message",
            equalTo("Access denied. Please, try to login again or contact your admin.")))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturn403WhenMissingAuthToken() throws Exception {
    mockMvc.perform(delete("/members/{id}", "1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.statusCode", equalTo(403)))
        .andExpect(jsonPath("$.message",
            equalTo("Access denied. Please, try to login again or contact your admin.")))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldReturn404WhenMemberDoesNotExist() throws Exception {
    String nonExistMemberId = "1000000";

    mockMvc.perform(delete("/members/{id}", nonExistMemberId)
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, getAuthorizationTokenForAdminUser()))
        .andExpect(jsonPath("$.statusCode", equalTo(404)))
        .andExpect(jsonPath("$.message", equalTo("Record not found in database.")))
        .andExpect(jsonPath("$.moreInfo", hasSize(1)))
        .andExpect(jsonPath("$.moreInfo", hasItem("Member not found.")))
        .andExpect(status().isNotFound());
  }

  private void assertMemberHasBeenDeleted(Long memberId) {
    Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberId);
    optionalMemberEntity.ifPresent(memberEntity -> assertTrue(memberEntity.getSoftDelete()));
  }

}
