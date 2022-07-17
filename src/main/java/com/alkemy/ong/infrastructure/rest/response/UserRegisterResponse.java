package com.alkemy.ong.infrastructure.rest.response;

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
public class UserRegisterResponse {

  @Schema(example = "Juana")
  private String firstName;

  @Schema(example = "Gomez")
  private String lastName;

  @Schema(example = "hola@gmail.com")
  private String email;

  @Schema(example = "Bearer token")
  private String token;

}
