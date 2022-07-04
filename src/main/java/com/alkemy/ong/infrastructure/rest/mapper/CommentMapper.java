package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public Comment toDomain(CommentRequest commentRequest) {
    if (commentRequest == null) {
      return null;
    }
    return Comment.builder()
            .body(commentRequest.getBody())
            .createBy(commentRequest.getCreateBy())
            .associatedNews(commentRequest.getAssociatedNews())
            .build();
  }

  public CommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    return CommentResponse.builder()
            .id(comment.getId())
            .body(comment.getBody())
            .createBy(comment.getCreateBy())
            .associatedNews(comment.getAssociatedNews())
            .createTimestamp(comment.getCreateTimestamp())
            .build();
  }

}
