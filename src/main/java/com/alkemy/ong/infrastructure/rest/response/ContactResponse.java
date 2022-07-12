package com.alkemy.ong.infrastructure.rest.response;

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
public class ContactResponse {

  private String name;
  private String phone;
  private String email;
  private String message;
}
