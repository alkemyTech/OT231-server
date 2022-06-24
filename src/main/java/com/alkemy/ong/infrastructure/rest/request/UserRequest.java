package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {

  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$")
  private String firstName;

  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$")
  private String lastName;

  @Email
  private String email;

  @Size(min = 6, max = 8)
  private String password;

}
