package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.UserFullName;
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
            .createBy(getUserFullName(commentEntity))
            .associatedNews(commentEntity.getNews().getName())
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

  private UserFullName getUserFullName(CommentEntity commentEntity) {
    return UserFullName.builder()
            .firstName(commentEntity.getUser().getFirstName())
            .lastName(commentEntity.getUser().getLastName())
            .build();
  }

}
