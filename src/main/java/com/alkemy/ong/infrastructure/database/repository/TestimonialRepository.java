package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class TestimonialRepository implements ITestimonialRepository {

  ITestimonialSpringRepository testimonialSpringRepository;

  @Override
  public Boolean existById(Long id) {
    return testimonialSpringRepository.existsById(id);
  }

  @Override
  public Boolean isDeleted(Long id) {
    return testimonialSpringRepository.isDeleted(id);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    testimonialSpringRepository.softDeleteById(id);
  }
}
