package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.User;

public interface IDeleteCommentUseCase {

  void delete(Long id, User user);
}
