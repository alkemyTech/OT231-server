package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IListUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements ICreateUserUseCase, IDeleteUserUseCase, IListUserUseCase {

  private final IUserRepository userRepository;

  @Override
  public User add(User newUser) {
    if (userRepository.findByEmail(newUser.getEmail()) != null) {
      throw new UserAlreadyExistsException(
          "Email address: " + newUser.getEmail() + " is already being used");
    }
    newUser.setRole(Role.USER.getFullRoleName());
    return userRepository.add(newUser);
  }

  @Override
  public void delete(Long id) {
    if (!userRepository.existsById(id) || userRepository.isDeleted(id)) {
      throw new RecordNotFoundException("User not found.");
    }
    userRepository.delete(id);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
