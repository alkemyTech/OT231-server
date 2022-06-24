package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.PasswordEncoder;
import com.alkemy.ong.infrastructure.rest.request.UserRequest;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User toDomain(UserRequest user) {
    if (user == null) {
      return null;
    }

    return User.builder().firstName(user.getFirstName()).lastName(user.getLastName())
        .email(user.getEmail()).password(passwordEncoder.encoder().encode(user.getPassword()))
        .build();
  }

  public UserResponse toResponse(User newUser) {
    if (newUser == null) {
      return null;
    }

    return UserResponse.builder().firstName(newUser.getFirstName()).lastName(newUser.getLastName())
        .email(newUser.getEmail()).build();
  }
}
