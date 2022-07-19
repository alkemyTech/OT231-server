package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.ListComments;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NewsEntityMapper {

  @Autowired
  private CommentEntityMapper commentEntityMapper;

  public NewsEntity toEntity(News news) {
    if (news == null) {
      return null;
    }
    return NewsEntity.builder()
        .id(news.getId())
        .name(news.getName())
        .content(news.getText())
        .image(news.getImage())
        .softDelete(news.isSoftDelete())
        .build();
  }

  public News toDomain(NewsEntity newsEntity) {
    if (newsEntity == null) {
      return null;
    }
    return News.builder()
        .id(newsEntity.getId())
        .name(newsEntity.getName())
        .text(newsEntity.getContent())
        .image(newsEntity.getImage())
        .build();
  }

  public ListComments toDomain(List<CommentEntity> commentEntities) {
    return commentEntityMapper.toDomain(commentEntities);
  }




}
