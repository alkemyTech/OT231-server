package com.alkemy.ong.infrastructure.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

  @Schema(example = "hola@gmail.com")
  @Email(message = "Email has invalid format.")
  @NotBlank(message = "Email cannot be null or empty.")
  private String email;

  @Schema(example = "abcd1234")
  @NotBlank(message = "Password cannot be null or empty.")
  @Size(min = 6, max = 8, message = "Password must be between 6 and 8 characters.")
  private String password;

}
