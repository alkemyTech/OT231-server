package com.alkemy.ong.infrastructure.rest.mapper;

import org.springframework.stereotype.Component;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.UserRequest;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;

@Component
public class UserMapper {

  public User toDomain(UserRequest user) {
    if (user == null) {
      return null;
    }

    return User.builder().firstName(user.getFirstName()).lastName(user.getLastName())
        .email(user.getEmail())
        // TODO Encriptar password
        .password(user.getPassword()).build();
  }

  public UserResponse toResponse(User newUser) {
    if (newUser == null) {
      return null;
    }

    return UserResponse.builder().firstName(newUser.getFirstName()).lastName(newUser.getLastName())
        .email(newUser.getEmail()).build();
  }
}
