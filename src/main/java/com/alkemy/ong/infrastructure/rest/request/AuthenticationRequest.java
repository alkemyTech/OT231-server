package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

  @Email(message = "Email has invalid format.")
  @NotBlank(message = "Email cannot be null or empty.")
  private String email;

  @NotBlank(message = "Password cannot be null or empty.")
  @Length(min = 6, max = 8, message = "The password must be between 6 and 8 characters.")
  private String password;

}
