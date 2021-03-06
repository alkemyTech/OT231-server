package com.alkemy.ong.infrastructure.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterResponse {

  @Schema(example = "Juana")
  private String firstName;

  @Schema(example = "Gomez")
  private String lastName;

  @Schema(example = "juanago@gmail.com")
  private String email;

  @Schema(example = "Bearer token")
  private String token;

}
