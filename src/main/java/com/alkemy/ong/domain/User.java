package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

  private String firstName;

  private String lastName;

  private String email;

  private String password;

}
