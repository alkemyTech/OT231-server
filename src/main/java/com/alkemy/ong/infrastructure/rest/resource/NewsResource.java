package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateNewsUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.usecase.IGetNewsUseCase;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.mapper.NewsMapper;
import com.alkemy.ong.infrastructure.rest.request.NewsRequest;
import com.alkemy.ong.infrastructure.rest.response.NewsResponse;
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
public class NewsResource {

  @Autowired
  private IDeleteNewsUseCase deleteNewsUseCase;

  @Autowired
  private ICreateNewsUseCase createNewsUseCase;

  @Autowired
  private IGetNewsUseCase getNewsUseCase;

  @Autowired
  private NewsMapper newsMapper;

  @DeleteMapping("/news/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteNewsUseCase.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(value = "/news",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<NewsResponse> create(@Valid @RequestBody NewsRequest createRequest) {
    News news = newsMapper.toDomain(createRequest);
    NewsResponse response = newsMapper.toResponse(createNewsUseCase.add(news));
    return new ResponseEntity<NewsResponse>(response, HttpStatus.CREATED);
  }

  @GetMapping(value = "/news/{id}", produces = {"application/json"})
  public ResponseEntity<NewsResponse> getBy(@PathVariable Long id) {
    return ResponseEntity.ok().body(newsMapper.toResponse(getNewsUseCase.getOne(id)));
  }

}
