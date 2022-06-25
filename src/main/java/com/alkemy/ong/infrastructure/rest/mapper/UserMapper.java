package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.UserRegisterResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User toDomain(UserRegisterRequest user) {
    if (user == null) {
      return null;
    }

    return User.builder().firstName(user.getFirstName()).lastName(user.getLastName())
        .email(user.getEmail()).password(passwordEncoder.encode(user.getPassword())).build();
  }

  public UserRegisterResponse toResponse(User newUser) {
    if (newUser == null) {
      return null;
    }

    return UserRegisterResponse.builder().firstName(newUser.getFirstName())
        .lastName(newUser.getLastName()).email(newUser.getEmail()).build();
  }
}
