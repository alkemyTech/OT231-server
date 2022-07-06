package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.infrastructure.rest.mapper.SlideMapper;
import com.alkemy.ong.infrastructure.rest.response.ListSlideResponse;
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

  @GetMapping(value = "/slides", produces = {"application/json"})
  public ResponseEntity<ListSlideResponse> list() {
    return ResponseEntity.ok().body(slideMapper
        .toResponse(listSlideUseCase.findAllByOrderByOrder()));
  }

  @DeleteMapping(value = "/slides/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteSlideUseCase.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

}
