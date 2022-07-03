package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.mapper.CategoryMapper;
import com.alkemy.ong.infrastructure.rest.request.CategoryRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryResponse;

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
public class CategoryResourse {

  @Autowired
  private IDeleteCategoryUseCase deleteCategoryUseCase;

  @Autowired
  private ICreateCategoryUseCase createCategoryUseCase;

  @Autowired
  private CategoryMapper categoryMapper;

  @PostMapping(value = "/categories",
          produces = {"application/json"},
          consumes = {"application/json"})
  public ResponseEntity<CategoryResponse> create(
          @Valid @RequestBody CategoryRequest categoryRequest) {
    Category category = categoryMapper.toDomain(categoryRequest);
    CategoryResponse response = categoryMapper.toResponse(createCategoryUseCase.add(category));
    return new ResponseEntity<CategoryResponse>(response, HttpStatus.CREATED);
  }


  @DeleteMapping("/categories/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteCategoryUseCase.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
