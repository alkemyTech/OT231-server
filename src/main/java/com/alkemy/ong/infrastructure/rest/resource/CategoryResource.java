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
import com.alkemy.ong.infrastructure.util.HeaderOnPagedResourceRetrieval;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

  @Autowired
  private HeaderOnPagedResourceRetrieval headerOnPagedResourceRetrieval;

  @PostMapping(value = "/categories",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<CategoryResponse> create(
      @Valid @RequestBody CategoryRequest categoryRequest) {
    Category category = categoryMapper.toDomain(categoryRequest);
    CategoryResponse response = categoryMapper.toResponse(createCategoryUseCase.add(category));
    return new ResponseEntity<CategoryResponse>(response, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/categories/{id}", produces = {"application/json"})
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteCategoryUseCase.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/categories/{id}", produces = {"application/json"})
  public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
    CategoryResponse categoryResponse = categoryMapper.toResponse(getCategoryUseCase.findById(id));
    return ResponseEntity.ok(categoryResponse);
  }

  @GetMapping(value = "/categories", produces = {"application/json"})
  public ResponseEntity<ListCategoryResponse> list(@PageableDefault(size = 10)
      Pageable pageable,
      UriComponentsBuilder uriBuilder,
      HttpServletResponse response) {
    Page<Category> resultPage = listCategoryUseCase.findAll(pageable);
    headerOnPagedResourceRetrieval.addLinkHeaderOnPagedResourceRetrieval(
        uriBuilder,
        response,
        "/categories",
        resultPage.getNumber(),
        resultPage.getTotalPages(),
        resultPage.getSize()
    );
    ListCategoryResponse listCategoryResponse = categoryMapper.toResponse(resultPage);
    return ResponseEntity.ok().body(listCategoryResponse);
  }

}
