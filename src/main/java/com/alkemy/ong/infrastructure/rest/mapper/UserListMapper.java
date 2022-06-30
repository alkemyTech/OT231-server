package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.response.UserListResponse;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserListMapper {

  public UserListResponse toResponse(List<User> usersList) {
    if (usersList == null || usersList.isEmpty()) {
      return new UserListResponse(Collections.emptyList());
    }

    List<UserResponse> userResponses = new ArrayList<>(usersList.size());
    for (User user : usersList) {
      userResponses.add(new UserResponse(user.getEmail()));
    }
    return new UserListResponse(userResponses);
  }
}