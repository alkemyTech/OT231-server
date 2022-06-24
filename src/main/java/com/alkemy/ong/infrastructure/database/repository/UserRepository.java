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

  private static final Role DEFAULT_ROLE = Role.USER;

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
    if (roleSpringRepository.findByName(DEFAULT_ROLE.getFullRoleName()) != null) {
      return roleSpringRepository.findByName(DEFAULT_ROLE.getFullRoleName());
    }
    return roleSpringRepository.save(RoleEntity.builder().description(DEFAULT_ROLE.name())
        .name(DEFAULT_ROLE.getFullRoleName()).build());
  }

}
