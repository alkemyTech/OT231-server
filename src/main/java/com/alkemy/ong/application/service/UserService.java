package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IGetAuthDetailsUseCase;
import com.alkemy.ong.application.service.usecase.IListUserUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateUserUseCase;
import com.alkemy.ong.application.util.ISendEmail;
import com.alkemy.ong.application.util.template.ContactEmailTemplate;
import com.alkemy.ong.application.util.template.WelcomeEmailTemplate;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class UserService implements ICreateUserUseCase, IDeleteUserUseCase, IListUserUseCase,
    IGetAuthDetailsUseCase, IUpdateUserUseCase {

  private final IUserRepository userRepository;
  private final IOrganizationRepository organizationRepository;
  private final ISendEmail sendEmail;

  @Override
  public User add(User newUser) {
    if (userRepository.findByEmail(newUser.getEmail()) != null) {
      throw new UserAlreadyExistsException(
          "Email address: " + newUser.getEmail() + " is already being used");
    }
    newUser.setRole(com.alkemy.ong.domain.Role.builder()
        .name(Role.USER.getFullRoleName())
        .build());
    User user = userRepository.add(newUser);
    sendWelcomeMail(user);
    return user;
  }

  private void sendWelcomeMail(User user) {
    try {
      ContactEmailTemplate contactEmailTemplate = new ContactEmailTemplate(user);
      WelcomeEmailTemplate welcomeMail = new WelcomeEmailTemplate(
          organizationRepository.find(), contactEmailTemplate);
      sendEmail.execute(welcomeMail);
    } catch (Exception exception) {
      log.error("Something went wrong sending the email. Reason: " + exception.getMessage());
    }
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
    return userRepository.findAllActive();
  }

  @Override
  public User getAuthDetails(User user) {
    return getUserBy(user.getEmail());
  }

  @Override
  public User update(User updatedUser) {
    User savedUser = userRepository.findBy(updatedUser.getId());
    if (savedUser == null) {
      throw new RecordNotFoundException("User not found.");
    }
    updateUserValues(updatedUser, savedUser);
    return userRepository.update(savedUser);
  }

  private User getUserBy(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new RecordNotFoundException("User not found.");
    }
    return user;
  }

  private void updateUserValues(User updatedUser, User savedUser) {
    updateCredentials(savedUser, updatedUser);
    updateOptionalInfo(savedUser, updatedUser);
  }

  private void updateCredentials(User savedUser, User updatedUser) {
    savedUser.setEmail(updatedUser.getEmail());
    savedUser.setPassword(updatedUser.getPassword());
  }

  private void updateOptionalInfo(User savedUser, User updatedUser) {
    savedUser.setFirstName(updatedUser.getFirstName());
    savedUser.setLastName(updatedUser.getLastName());
    savedUser.setPhoto(updatedUser.getPhoto());
  }
}
