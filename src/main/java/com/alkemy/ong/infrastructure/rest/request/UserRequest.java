package com.alkemy.ong.infrastructure.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {

  private String firstName;

  private String lastName;

  private String email;

  private String password;

}
