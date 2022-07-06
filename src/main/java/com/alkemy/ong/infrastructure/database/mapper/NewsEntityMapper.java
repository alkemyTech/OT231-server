package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.News;
//import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsEntityMapper {

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

  // public Slide getSlide(Slide slide){
  // return Slide.builder()
  //   .order(slide.getOrder())
  // .imageUrl(slide.getImageUrl())
  //.text(slide.getText())
  //.build();
  //}

}
