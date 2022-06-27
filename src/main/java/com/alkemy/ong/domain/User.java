package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String photo;
  private String token;
  private String role;

}
