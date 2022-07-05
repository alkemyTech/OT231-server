package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {

  public Comment toDomain(CommentEntity commentEntity) {
    if (commentEntity == null) {
      return null;
    }
    return Comment.builder()
            .id(commentEntity.getId())
            .body(commentEntity.getBody())
            .userId(commentEntity.getUser().getId())
            .newsId(commentEntity.getNews().getId())
            .createTimestamp(commentEntity.getCreateTimestamp())
            .build();
  }

  public CommentEntity toEntity(Comment comment) {
    if (comment == null) {
      return null;
    }
    return CommentEntity.builder()
            .id(comment.getId())
            .body(comment.getBody())
            .createTimestamp(comment.getCreateTimestamp())
            .build();
  }
}
