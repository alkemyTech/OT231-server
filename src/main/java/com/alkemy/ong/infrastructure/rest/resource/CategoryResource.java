package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IListCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.mapper.CategoryMapper;
import com.alkemy.ong.infrastructure.rest.request.CategoryRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.ListCategoryResponse;
import java.util.List;
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
public class CategoryResource {

  @Autowired
  private IDeleteCategoryUseCase deleteCategoryUseCase;

  @Autowired
  private ICreateCategoryUseCase createCategoryUseCase;

  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private IListCategoryUseCase listCategoryUseCase;

  @Autowired
  private IGetCategoryUseCase getCategoryUseCase;

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

  @GetMapping(value = "/categories", produces = {"application/json"})
  public ResponseEntity<ListCategoryResponse> list() {
    List<Category> categories = listCategoryUseCase.findAll();
    return ResponseEntity.ok().body(categoryMapper.toResponse(categories));
  }

  @GetMapping(value = "/categories/{id}", produces = {"application/json"})
  public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
    CategoryResponse categoryResponse = categoryMapper.toResponse(getCategoryUseCase.findById(id));
    return ResponseEntity.ok(categoryResponse);
  }
}
