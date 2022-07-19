package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.ListComments;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {

  @Autowired
  private UserEntityMapper userEntityMapper;

  @Autowired
  private NewsEntityMapper newsEntityMapper;

  public Comment toDomain(CommentEntity commentEntity) {
    if (commentEntity == null) {
      return null;
    }
    return Comment.builder()
        .id(commentEntity.getId())
        .body(commentEntity.getBody())
        .user(userEntityMapper.toDomain(commentEntity.getUser()))
        .news(newsEntityMapper.toDomain(commentEntity.getNews()))
        .createTimestamp(commentEntity.getCreateTimestamp())
        .softDelete(commentEntity.getSoftDelete())
        .build();
  }

  public ListComments toDomain(List<CommentEntity> commentEntities) {
    if (commentEntities == null || commentEntities.isEmpty()) {
      return new ListComments(Collections.emptyList());
    }
    List<Comment> comments = new ArrayList<>(commentEntities.size());
    for (CommentEntity commentEntity : commentEntities) {
      comments.add(toDomain(commentEntity));
    }
    return new ListComments(comments);
  }

  public CommentEntity toEntity(Comment comment) {
    if (comment == null) {
      return null;
    }
    return CommentEntity.builder()
        .id(comment.getId())
        .body(comment.getBody())
        .user(userEntityMapper.toEntity(comment.getUser()))
        .news(newsEntityMapper.toEntity(comment.getNews()))
        .createTimestamp(comment.getCreateTimestamp())
        .softDelete(comment.getSoftDelete())
        .build();
  }
}
