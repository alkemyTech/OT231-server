package com.alkemy.ong.infrastructure.rest.request;

import org.springframework.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserRequest extends UserRegisterRequest {
  
  @Nullable
  String photo;

}
