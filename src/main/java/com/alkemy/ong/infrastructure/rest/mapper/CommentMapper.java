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
    return Comment.builder()
            .body(commentRequest.getBody())
            .userId(commentRequest.getUserId())
            .newsId(commentRequest.getNewsId())
            .build();
  }

  public CommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    return CommentResponse.builder()
            .id(comment.getId())
            .body(comment.getBody())
            //.createBy(getFullNameResponse(comment.getUser()))
            //.associatedNews(comment.getNewsId())
            .createTimestamp(comment.getCreateTimestamp())
            .build();
  }

  /*
  private FullNameResponse getFullNameResponse(User user) {
    return FullNameResponse.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();
  }*/



}
