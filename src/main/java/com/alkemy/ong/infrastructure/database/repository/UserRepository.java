package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserRepository implements IUserRepository {

  private final IUserSpringRepository userSpringRepository;
  private final IRoleSpringRepository roleSpringRepository;
  private final UserEntityMapper userEntityMapper;

  private String defaultRole = Role.USER.name();

  @Override
  public User findByEmail(String email) {
    return userEntityMapper.toDomain(userSpringRepository.findByEmail(email));
  }

  @Override
  @Transactional
  public User addUser(User newUser) {
    UserEntity user = userEntityMapper.toEntity(newUser);
    user.setRole(getDefaultRole());
    return userEntityMapper.toDomain(userSpringRepository.save(user));
  }

  private RoleEntity getDefaultRole() {
    if (roleSpringRepository.findByName(defaultRole) != null) {
      return roleSpringRepository.findByName(defaultRole);
    }
    RoleEntity role = new RoleEntity();
    role.setName(defaultRole);
    return roleSpringRepository.save(role);
  }

}
