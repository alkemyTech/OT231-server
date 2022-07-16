package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.usecase.ICreateTestimonialUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.domain.Testimonial;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TestimonialService implements IDeleteTestimonialUseCase, ICreateTestimonialUseCase {

  private final ITestimonialRepository testimonialRepository;

  @Override
  public void delete(Long id) {
    if (!testimonialRepository.existById(id) || testimonialRepository.isDeleted(id)) {
      throw new RecordNotFoundException("Testimonial not found.");
    }
    testimonialRepository.delete(id);
  }

  @Override
  public Testimonial add(Testimonial newTestimonial) {
    return testimonialRepository.add(newTestimonial);
  }
}
