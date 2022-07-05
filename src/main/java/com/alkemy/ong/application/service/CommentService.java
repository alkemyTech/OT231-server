package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentService implements ICreateCommentUseCase {

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

}
