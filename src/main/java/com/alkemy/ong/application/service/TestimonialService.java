package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.InvalidCredentialsException;
import com.alkemy.ong.application.exception.TestimonialNotFoundException;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TestimonialService implements IDeleteTestimonialUseCase {

  ITestimonialRepository testimonialRepository;

  @Override
  public void delete(Long id) {
    if (!testimonialRepository.existById(id) || testimonialRepository.isDeleted(id)) {
      throw new TestimonialNotFoundException("Testimonial not found");
    }
    testimonialRepository.delete(id);
  }
}
