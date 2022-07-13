package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import com.alkemy.ong.infrastructure.database.mapper.TestimonialsEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ITestimonialSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class TestimonialRepository implements ITestimonialRepository {

  private final ITestimonialSpringRepository testimonialSpringRepository;

  private final TestimonialsEntityMapper testimonialsEntityMapper;

  @Override
  public boolean existById(Long id) {
    return testimonialSpringRepository.existsById(id);
  }

  @Override
  public boolean isDeleted(Long id) {
    return testimonialSpringRepository.isDeleted(id).isPresent();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    testimonialSpringRepository.softDeleteById(id);
  }

  @Override
  public Testimonial add(Testimonial newTestimonial) {
    TestimonialEntity testimonialEntity = testimonialsEntityMapper.toEntity(newTestimonial);
    return testimonialsEntityMapper.toDomain(testimonialSpringRepository.save(testimonialEntity));
  }
}
