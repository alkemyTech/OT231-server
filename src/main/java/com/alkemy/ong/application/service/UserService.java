package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IGetUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.JwtUtils;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements ICreateUserUseCase, IDeleteUserUseCase, IGetUserUseCase {

  private final IUserRepository userRepository;

  private final JwtUtils jwtUtils;

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
  public User getDetails(String token) {
    String email = jwtUtils.extractUsername(token);
    return getUserBy(email);
  }

  private User getUserBy(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new RecordNotFoundException("User not found.");
    }
    return user;
  }
  
}
