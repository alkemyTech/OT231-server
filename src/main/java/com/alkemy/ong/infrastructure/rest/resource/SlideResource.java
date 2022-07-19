package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateSlideUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IGetSlideUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.mapper.SlideMapper;
import com.alkemy.ong.infrastructure.rest.request.SlideRequest;
import com.alkemy.ong.infrastructure.rest.response.ListSlideResponse;
import com.alkemy.ong.infrastructure.rest.response.SlideResponse;
import com.alkemy.ong.infrastructure.rest.response.field.SlideResponseField;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlideResource {

  @Autowired
  private IListSlideUseCase listSlideUseCase;

  @Autowired
  private SlideMapper slideMapper;

  @Autowired
  private IDeleteSlideUseCase deleteSlideUseCase;

  @Autowired
  private IGetSlideUseCase getSlideUseCase;

  @Autowired
  private ICreateSlideUseCase createSlideUseCase;

  private static final SlideResponseField[] SLIDE_RESPONSE_FIELDS =
      {SlideResponseField.IMAGE_URL, SlideResponseField.ORDER, SlideResponseField.TEXT};

  @GetMapping(value = "/slides", produces = {"application/json"})
  public ResponseEntity<ListSlideResponse> list() {
    return ResponseEntity.ok().body(slideMapper
        .toResponse(listSlideUseCase.findAllByOrderByOrder()));
  }

  @DeleteMapping(value = "/slides/{id}", produces = {"application/json"})
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteSlideUseCase.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/slides/{id}", produces = {"application/json"})
  public ResponseEntity<SlideResponse> getBy(@PathVariable("id") Long id) {
    SlideResponse response =
        slideMapper.toResponse(getSlideUseCase.findBy(id), SLIDE_RESPONSE_FIELDS);
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/slides",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<SlideResponse> create(@Valid @RequestBody SlideRequest slideRequest) {
    Slide slide = createSlideUseCase.create(slideMapper.toDomain(slideRequest));
    SlideResponse response = slideMapper.toResponse(slide, SLIDE_RESPONSE_FIELDS);
    return new ResponseEntity<SlideResponse>(response, HttpStatus.CREATED);
  }

}
