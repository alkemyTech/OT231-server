package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class ContactRequest {

  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$",
      message = "Name can only contain letters and whitespaces")
  private String name;

  private String phone;

  @Email(message = "Email should be valid")
  private String email;

  @Size(max = 150, message = "Message must have a maximum of 150 characters")
  @Pattern(regexp = "^[\\p{L}\\d\\s.,;:]*$", message = "Message can only contain "
      + "alphanumeric and whitespaces")
  private String message;

}
