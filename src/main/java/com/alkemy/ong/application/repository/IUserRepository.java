package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.User;
import java.util.List;

public interface IUserRepository {

  User findByEmail(String email);

  User add(User user);

  boolean existsById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

  List<User> findAllActive();

  User findBy(Long id);

  User update(User user);

}
