package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.OperationNotPermittedException;
import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentService implements ICreateCommentUseCase, IDeleteCommentUseCase {

  private final ICommentRepository commentRepository;
  private final INewsRepository newsRepository;
  private final IUserRepository userRepository;

  @Override
  public Comment add(Comment newComment) {
    return commentRepository.add(
        Comment.builder()
            .body(newComment.getBody())
            .user(getUserBy(newComment.getUser().getId()))
            .news(getNewsBy(newComment.getNews().getId())).build());
  }

  private User getUserBy(Long id) {
    User user = userRepository.findBy(id);
    if (user == null) {
      throw new RecordNotFoundException("User not found.");
    }
    return user;
  }

  private News getNewsBy(Long id) {
    News news = newsRepository.findBy(id);
    if (news == null) {
      throw new RecordNotFoundException("News not found.");
    }
    return news;
  }

  @Override
  public void delete(Comment commentToDelete) {
    Comment comment = findBy(commentToDelete.getId());
    User authenticatedUser = commentToDelete.getUser();
    if (!isSameUser(comment, authenticatedUser) && isNotAdmin(authenticatedUser)) {
      throw new OperationNotPermittedException("No permission to delete this comment.");
    }
    comment.setSoftDelete(true);
    commentRepository.save(comment);
  }

  private boolean isSameUser(Comment comment, User authenticatedUser) {
    return authenticatedUser.getEmail().equals(comment.getUser().getEmail());
  }

  private boolean isNotAdmin(User authenticatedUser) {
    return !Role.ADMIN.getFullRoleName().equals(authenticatedUser.getRole().getName());
  }

  private Comment findBy(Long id) {
    Comment comment = commentRepository.findById(id).orElse(null);
    if (comment == null || isDeleted(comment)) {
      throw new RecordNotFoundException("Comment not found.");
    }
    return comment;
  }

  private boolean isDeleted(Comment comment) {
    Boolean softDelete = comment.getSoftDelete();
    return !(softDelete == null || Boolean.FALSE.equals(softDelete));
  }

}
