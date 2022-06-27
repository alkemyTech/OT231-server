package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestimonialResource {

  @Autowired
  IDeleteTestimonialUseCase deleteTestimonialUseCase;

  @DeleteMapping("/testimonials/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteTestimonialUseCase.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}