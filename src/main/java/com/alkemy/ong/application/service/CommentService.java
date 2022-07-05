package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.domain.Comment;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentService implements ICreateCommentUseCase {

  private final ICommentRepository commentRepository;

  @Override
  public Comment add(Comment newComment) {
    return commentRepository.add(newComment);
  }

}
