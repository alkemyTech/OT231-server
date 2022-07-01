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

  public UserListResponse listDomain2Response(List<User> listUsers) {
    if (listUsers == null || listUsers.isEmpty()) {
      return new UserListResponse(Collections.emptyList());
    }
    List<UserResponse> listUsersResponses = new ArrayList<>(listUsers.size());
    for (User user : listUsers) {
      listUsersResponses.add(new UserResponse(user.getEmail()));
    }
    return new UserListResponse(listUsersResponses);
  }
}