package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.AuthenticationRequest;
import com.alkemy.ong.infrastructure.rest.response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

  public User toDomain(AuthenticationRequest authenticationRequest) {
    if (authenticationRequest == null) {
      return null;
    }
    return User.builder()
        .email(authenticationRequest.getEmail())
        .password(authenticationRequest.getPassword())
        .build();
  }

  public AuthenticationResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    return AuthenticationResponse.builder()
        .email(user.getEmail())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .photo(user.getPhoto())
        .token(user.getToken())
        .build();
  }

}
