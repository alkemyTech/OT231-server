package com.alkemy.ong.infrastructure.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

  @Schema(example = "juanago@gmail.com")
  private String email;

  @Schema(example = "Juana")
  private String firstName;

  @Schema(example = "Gomez")
  private String lastName;

  @Schema(example = "image.jpg")
  private String photo;

  @Schema(example = "Bearer token")
  private String token;

}