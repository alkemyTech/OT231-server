package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.InvalidCredentialsException;
import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ILoginUseCase;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
public class AuthenticationService implements ILoginUseCase {

  private final AuthenticationManager authenticationManager;
  private final IUserRepository userRepository;

  @Override
  public User login(User user) {
    authenticate(user);
    return getUserBy(user.getEmail());
  }

  private User getUserBy(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new RecordNotFoundException("User not found");
    }
    return user;
  }

  private void authenticate(User user) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          user.getEmail(),
          user.getPassword()));
    } catch (Exception e) {
      throw new InvalidCredentialsException("Invalid email or password.");
    }
  }

}
