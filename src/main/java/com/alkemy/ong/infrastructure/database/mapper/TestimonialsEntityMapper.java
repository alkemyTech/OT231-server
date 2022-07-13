package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import org.springframework.stereotype.Component;

@Component
public class TestimonialsEntityMapper {

  public Testimonial toDomain(TestimonialEntity testimonialEntity) {
    if (testimonialEntity == null) {
      return null;
    }
    return Testimonial.builder()
            .name(testimonialEntity.getName())
            .content(testimonialEntity.getContent())
            .image(testimonialEntity.getImage())
            .build();
  }

  public TestimonialEntity toEntity(Testimonial testimonial) {
    if (testimonial == null) {
      return null;
    }
    return TestimonialEntity.builder()
          .name(testimonial.getName())
          .content(testimonial.getContent())
          .image(testimonial.getImage())
          .build();
  }
}
