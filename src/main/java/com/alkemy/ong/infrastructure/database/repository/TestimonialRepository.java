package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class TestimonialRepository implements ITestimonialRepository {

  @Autowired
  private final ITestimonialSpringRepository testimonialSpringRepository;

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
}
