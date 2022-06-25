package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements ICreateUserUseCase {

  private final IUserRepository userRepository;

  @Override
  public User add(User newUser) {
    User user = userRepository.findByEmail(newUser.getEmail());
    if (user != null) {
      throw new UserAlreadyExistsException(
          "This email address: " + newUser.getEmail() + " is already being used");
    }
    return userRepository.add(newUser);
  }

}
