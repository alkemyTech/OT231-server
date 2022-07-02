package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

}


