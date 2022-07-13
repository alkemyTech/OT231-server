package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.request.TestimonialRequest;
import com.alkemy.ong.infrastructure.rest.response.TestimonialResponse;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

  public Testimonial toDomain(TestimonialRequest testimonialRequest) {
    if (testimonialRequest == null) {
      return null;
    }
    return Testimonial.builder()
          .name(testimonialRequest.getName())
          .content(testimonialRequest.getContent())
          .image(testimonialRequest.getImage())
          .build();
  }

  public TestimonialResponse toResponse(Testimonial testimonial) {
    if (testimonial == null) {
      return null;
    }

    return TestimonialResponse.builder()
          .name(testimonial.getName())
          .content(testimonial.getContent())
          .image(testimonial.getImage())
          .build();
  }
}
