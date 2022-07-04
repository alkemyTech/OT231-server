package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.JwtUtils;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

  @Autowired
  private JwtUtils jwtUtils;

  public List<User> toDomain(List<UserEntity> userEntities) {
    if (userEntities == null || userEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<User> users = new ArrayList<>(userEntities.size());
    for (UserEntity userEntity : userEntities) {
      users.add(toDomain(userEntity));
    }
    return users;
  }

  public User toDomain(UserEntity userEntity) {
    if (userEntity == null) {
      return null;
    }
    return User.builder()
        .email(userEntity.getEmail())
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .photo(userEntity.getPhoto())
        .password(userEntity.getPassword())
        .token(jwtUtils.generateToken(userEntity))
        .build();
  }

  public UserEntity toEntity(User user) {
    if (user == null) {
      return null;
    }
    return UserEntity.builder()
        .email(user.getEmail())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .photo(user.getPhoto())
        .password(user.getPassword())
        .build();
  }

}
