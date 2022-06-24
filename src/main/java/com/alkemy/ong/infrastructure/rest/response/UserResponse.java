package com.alkemy.ong.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
public class UserResponse {

  @Email
  private String email;

  @Size(min = 6 , max = 8)
  private String password;


}
