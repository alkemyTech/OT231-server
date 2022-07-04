package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.UserFullName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentService implements ICreateCommentUseCase {

  private final ICommentRepository commentRepository;
  private final IUserRepository userRepository;

  @Override
  public Comment add(Comment newComment, UserFullName userFullName, String name) {
    if (userRepository.findByFirstNameAndLastName(
                    userFullName.getFirstName(), userFullName.getLastName()) == null) {
      throw new RecordNotFoundException("User not found.");
    }

    newComment.setCreateBy(userFullName);
    newComment.setAssociatedNews(name);
    return commentRepository.add(newComment);
  }

}
