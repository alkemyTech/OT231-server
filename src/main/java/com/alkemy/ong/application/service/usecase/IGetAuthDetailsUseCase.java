package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.User;

public interface IGetAuthDetailsUseCase {

  User getAuthDetails(User user);

}
