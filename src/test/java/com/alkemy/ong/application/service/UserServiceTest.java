package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.domain.Role;
import com.alkemy.ong.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private static final String EMAIL = "bruce@wayne.com";
  private static final String ROLE = "ROLE_USER";

  private UserService userService;

  @Mock
  private IUserRepository userRepository;

  @BeforeEach
  void setup() {
    userService = new UserService(userRepository);
  }

  @Test
  void shouldThrowExceptionWhenUserAlreadyExist() {
    User user = User.builder().email(EMAIL).build();
    given(userRepository.findByEmail(EMAIL)).willReturn(user);

    assertThrows(UserAlreadyExistsException.class, () -> userService.add(user));
  }

  @Test
  void shouldSaveUserWhenUserDoesNotExist() {
    given(userRepository.findByEmail(EMAIL)).willReturn(null);
    User user = User.builder().email(EMAIL)
                 .role(Role.builder().name(ROLE).build())
               .build();

    userService.add(user);

    verify(userRepository).findByEmail(EMAIL);
    verify(userRepository).add(user);
  }

}