package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.User;

public interface IUserRepository {

  User findByEmail(String email);

  User add(User user);

}
