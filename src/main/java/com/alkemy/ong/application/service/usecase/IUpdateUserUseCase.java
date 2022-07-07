package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.User;

public interface IUpdateUserUseCase {

  User update(Long id, User user);

}
