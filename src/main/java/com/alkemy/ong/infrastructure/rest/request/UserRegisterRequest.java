package com.alkemy.ong.infrastructure.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserRegisterRequest {

  @Schema(example = "Juana")
  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$",
      message = "Name can only contain letters and whitespaces")
  private String firstName;

  @Schema(example = "Gomez")
  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$",
      message = "Lastname can only contain letters and whitespaces")
  private String lastName;

  @Schema(example = "juanago@gmail.com")
  @Email(message = "Email should be valid")
  private String email;

  @Schema(example = "abcd1234")
  @Size(min = 6, max = 8, message = "Password must be between 6 and 8 characters")
  private String password;

}