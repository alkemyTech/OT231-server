package com.alkemy.ong.infrastructure.database.mapper;

import org.springframework.stereotype.Component;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;

@Component
public class NewsEntityMapper {

  public NewsEntity toEntity(News news) {
    if (news == null) {
      return null;
    }
    return NewsEntity.builder()
        .name(news.getName())
        .content(news.getText())
        .image(news.getImage())
        .softDelete(false)
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

}
