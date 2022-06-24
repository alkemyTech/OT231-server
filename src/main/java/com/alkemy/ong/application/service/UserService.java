package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService implements ICreateUserUseCase {

  private final IUserRepository userRepository;

  @Override
  public User addUser(User newUser) {
    if (userRepository.findByEmail(newUser.getEmail()) != null) {
      // TODO "This email address is already being used"
    }
    return userRepository.addUser(newUser);
  }

}
