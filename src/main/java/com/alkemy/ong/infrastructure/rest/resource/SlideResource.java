package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IGetSlideUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.infrastructure.rest.mapper.SlideMapper;
import com.alkemy.ong.infrastructure.rest.response.ListSlideResponse;
import com.alkemy.ong.infrastructure.rest.response.SlideResponse;
import com.alkemy.ong.infrastructure.rest.response.field.SlideResponseField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    SlideResponseField[] slideResponseFields =
        {SlideResponseField.IMAGE_URL, SlideResponseField.ORDER, SlideResponseField.TEXT};

    SlideResponse response =
        slideMapper.toResponse(getSlideUseCase.findBy(id), slideResponseFields);
    return ResponseEntity.ok(response);
  }

}
