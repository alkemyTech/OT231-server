package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Role;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.JwtUtils;
import com.alkemy.ong.infrastructure.rest.request.UpdateUserRequest;
import com.alkemy.ong.infrastructure.rest.response.ListUserResponse;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  @Autowired
  private JwtUtils jwtUtils;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  public ListUserResponse toResponse(List<User> users) {
    if (users == null || users.isEmpty()) {
      return new ListUserResponse(Collections.emptyList());
    }

    List<UserResponse> userResponses = new ArrayList<>(users.size());
    for (User user : users) {
      userResponses.add(toResponse(user));
    }
    return new ListUserResponse(userResponses);
  }

  public UserResponse toResponse(User user) {
    return UserResponse.builder()
        .email(user.getEmail())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .photo(user.getPhoto())
        .build();
  }

  public User toDomain(String authorizationHeader) {
    return User.builder()
        .email(extractEmail(authorizationHeader))
        .role(Role.builder().name(extractAuthorities(authorizationHeader)).build())
        .build();
  }
  
  public User toDomain(Long id, UpdateUserRequest updateUserRequest) {
    return User.builder()
        .id(id)
        .firstName(updateUserRequest.getFirstName())
        .lastName(updateUserRequest.getLastName())
        .email(updateUserRequest.getEmail())
        .password(passwordEncoder.encode(updateUserRequest.getPassword()))
        .photo(updateUserRequest.getPhoto())
        .build();
  }

  private String extractEmail(String token) {
    return jwtUtils.extractUsername(token);
  }

  private String extractAuthorities(String token) {
    return jwtUtils.getAuthorities(token).stream().findFirst().get().toString();
  }

}