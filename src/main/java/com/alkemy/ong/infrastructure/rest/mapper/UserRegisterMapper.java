package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.UserRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User toDomain(UserRegisterRequest registerRequest) {
    if (registerRequest == null) {
      return null;
    }
    return User.builder()
        .firstName(registerRequest.getFirstName())
        .lastName(registerRequest.getLastName())
        .email(registerRequest.getEmail())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .build();
  }

  public UserRegisterResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    return UserRegisterResponse.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .token(user.getToken())
        .build();
  }
}
