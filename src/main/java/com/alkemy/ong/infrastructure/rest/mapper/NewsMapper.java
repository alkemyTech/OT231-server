package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.request.NewsRequest;
import com.alkemy.ong.infrastructure.rest.response.NewsResponse;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

  public News toDomain(NewsRequest news) {
    if (news == null) {
      return null;
    }
    return News.builder()
        .name(news.getName())
        .text(news.getText())
        .image(news.getImage())
        .build();
  }

  public NewsResponse toResponse(News news) {
    if (news == null) {
      return null;
    }
    return NewsResponse.builder()
        .id(news.getId())
        .name(news.getName())
        .text(news.getText())
        .image(news.getImage())
        .build();
  }

}
