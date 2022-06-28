package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.mapper.UserEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.IRoleSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.IUserSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class UserRepository implements IUserRepository {

  private final IUserSpringRepository userSpringRepository;
  private final IRoleSpringRepository roleSpringRepository;
  private final UserEntityMapper userEntityMapper;

  @Override
  public User findByEmail(String email) {
    return userEntityMapper.toDomain(userSpringRepository.findByEmail(email));
  }

  @Override
  @Transactional
  public User add(User newUser) {
    UserEntity userEntity = userEntityMapper.toEntity(newUser);
    userEntity.setRole(getRoleEntity(newUser.getRole()));
    return userEntityMapper.toDomain(userSpringRepository.save(userEntity));
  }

  @Override
  public boolean existsById(Long id) {
    return userSpringRepository.existsById(id);
  }

  @Override
  public boolean isDeleted(Long id) {
    return userSpringRepository.isDeleted(id).isPresent();
  }

  private RoleEntity getRoleEntity(String role) {
    return roleSpringRepository.findByName(role);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    userSpringRepository.softDeleteById(id);
  }
}
