package com.alkemy.ong.infrastructure.rest.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserResponse {

  @Schema(example = "juanago@gmail.com")
  private String email;

  @Schema(example = "Juana")
  private String firstName;

  @Schema(example = "Gomez")
  private String lastName;

  @Schema(example = "image.jpg")
  private String photo;

}