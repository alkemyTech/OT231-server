package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRepository implements IUserRepository {

  private final IUserSpringRepository userSpringRepository;
  private final UserEntityMapper userEntityMapper;

  @Override
  public User findByEmail(String email) {
    return userEntityMapper.toDomain(userSpringRepository.findByEmail(email));
  }

  @Override
  public User addUser(User newUser) {
    UserEntity user = userEntityMapper.toEntity(newUser);
    return userEntityMapper.toDomain(userSpringRepository.save(user));
  }

}
