package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class SlideEntityMapper {

  public List<Slide> toDomain(List<SlideEntity> slideEntities) {
    if (slideEntities == null || slideEntities.isEmpty()) {
      return Collections.emptyList();
    }

    List<Slide> slides = new ArrayList<>(slideEntities.size());

    for (SlideEntity slideEntity : slideEntities) {
      slides.add(Slide.builder()
          .imageUrl(slideEntity.getImageUrl())
          .order(slideEntity.getOrder())
          .build());
    }

    return slides;
  }

  public Slide toDomain(Optional<SlideEntity> slideEntity) {
    if (slideEntity.isEmpty()) {
      return null;
    }
    return Slide.builder()
        .imageUrl(slideEntity.get().getImageUrl())
        .order(slideEntity.get().getOrder())
        .text(slideEntity.get().getText())
        .build();
  }

  public SlideEntity toEntity(Slide slide) {
    if (slide == null) {
      return null;
    }
    return SlideEntity.builder()
        .imageUrl(slide.getImageUrl())
        .order(slide.getOrder())
        .text(slide.getText())
        .build();
  }

}


