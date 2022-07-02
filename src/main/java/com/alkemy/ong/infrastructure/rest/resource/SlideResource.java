package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.infrastructure.rest.mapper.SlideMapper;
import com.alkemy.ong.infrastructure.rest.response.ListSlideResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlideResource {

  @Autowired
  private IListSlideUseCase listSlideUseCase;

  @Autowired
  private SlideMapper slideMapper;

  @GetMapping(value = "/slides", produces = {"application/json"})
  public ResponseEntity<ListSlideResponse> list() {
    return ResponseEntity.ok().body(slideMapper
            .toResponse(listSlideUseCase.findAllByOrderByOrder()));
  }

}
