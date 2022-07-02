package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.mapper.CategoryCreateMapper;
import com.alkemy.ong.infrastructure.rest.request.CategoryCreateRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryCreateResponse;
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
public class CategoryResource {

  @Autowired
  private IDeleteCategoryUseCase deleteCategoryUseCase;

  @Autowired
  private CategoryCreateMapper createMapper;

  @Autowired
  private ICreateCategoryUseCase createUseCase;

  @DeleteMapping("/categories/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteCategoryUseCase.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @PostMapping(value = "/categories",
          produces = {"application/json"},
          consumes = {"application/json"})
  public ResponseEntity<CategoryCreateResponse> create(
        @Valid @RequestBody CategoryCreateRequest createRequest) {
    Category category = createMapper.toDomain(createRequest);
    CategoryCreateResponse response = createMapper.toResponse(createUseCase.add(category));
    return new ResponseEntity<CategoryCreateResponse>(response, HttpStatus.CREATED);
  }
}
