package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateTestimonialUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.mapper.TestimonialMapper;
import com.alkemy.ong.infrastructure.rest.request.TestimonialRequest;
import com.alkemy.ong.infrastructure.rest.response.TestimonialResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestimonialResource {

  @Autowired
  private IDeleteTestimonialUseCase deleteTestimonialUseCase;

  @Autowired
  private ICreateTestimonialUseCase createTestimonialUseCase;

  @Autowired
  private TestimonialMapper testimonialMapper;

  @DeleteMapping(value = "/testimonials/{id}", produces = {"application/json"})
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteTestimonialUseCase.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(value = "/testimonials",
          produces = {"application/json"},
          consumes = {"application/json"})
  public ResponseEntity<TestimonialResponse> create(
          @Valid @RequestBody TestimonialRequest testimonialRequest) {
    Testimonial testimonial = testimonialMapper.toDomain(testimonialRequest);
    TestimonialResponse response = testimonialMapper
            .toResponse(createTestimonialUseCase.add(testimonial));
    return new ResponseEntity<TestimonialResponse>(response, HttpStatus.CREATED);
  }
}