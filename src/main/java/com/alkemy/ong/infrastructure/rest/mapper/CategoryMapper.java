package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.request.CategoryRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.ListCategoryResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public Category toDomain(CategoryRequest categoryRequest) {
    if (categoryRequest == null) {
      return null;
    }
    return Category.builder()
        .name(categoryRequest.getName())
        .description(categoryRequest.getDescription())
        .image(categoryRequest.getImage())
        .build();
  }

  public CategoryResponse toResponse(Category category) {
    if (category == null) {
      return null;
    }
    return CategoryResponse.builder()
        .name(category.getName())
        .description(category.getDescription())
        .image(category.getImage())
        .build();
  }

  public ListCategoryResponse toResponse(List<Category> categories) {
    if (categories == null || categories.isEmpty()) {
      return new ListCategoryResponse(Collections.emptyList());
    }
    List<CategoryResponse> categoriesResponses = new ArrayList<>(categories.size());
    for (Category category : categories) {
      categoriesResponses.add(toResponse(category));
    }
    return new ListCategoryResponse(categoriesResponses);
  }

  public ListCategoryResponse toResponse(Page<Category> categoryPage) {
    ListCategoryResponse listCategoryResponse = toResponse(categoryPage.getContent());
    listCategoryResponse.setPage(categoryPage.getNumber());
    listCategoryResponse.setSize(categoryPage.getSize());
    listCategoryResponse.setTotalPages(categoryPage.getTotalPages());
    return listCategoryResponse;
  }

}
