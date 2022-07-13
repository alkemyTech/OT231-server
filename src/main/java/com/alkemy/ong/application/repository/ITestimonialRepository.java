package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Testimonial;

public interface ITestimonialRepository {

  boolean existById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

  Testimonial add(Testimonial newTestimonial);
}
