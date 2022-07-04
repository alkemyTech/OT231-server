package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.request.CategoryRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryResponse;
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
}
