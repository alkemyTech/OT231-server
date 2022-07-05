package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import com.alkemy.ong.infrastructure.rest.response.FullNameResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public Comment toDomain(CommentRequest commentRequest) {
    if (commentRequest == null) {
      return null;
    }
    Comment comment = new Comment();
    comment.setNewsId(commentRequest.getNewsId());
    comment.setUserId(commentRequest.getUserId());
    comment.setBody(commentRequest.getBody());
    return comment;
  }

  public CommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    return CommentResponse.builder()
        .id(comment.getId())
        .body(comment.getBody())
        .createdBy(getFullNameResponse(comment.getUser()))
        .associatedNews(comment.getNews().getName())
        .createTimestamp(comment.getCreateTimestamp())
        .build();
  }

  private FullNameResponse getFullNameResponse(User user) {
    return FullNameResponse.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .build();
  }

}
