package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.request.CategoryCreateRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryCreateResponse;

public class CategoryCreateMapper {

  public Category toDomain(CategoryCreateRequest createRequest) {
    if (createRequest == null) {
      return null;
    }
    return Category.builder()
              .name(createRequest.getName())
              .description(createRequest.getDescription())
              .image(createRequest.getImage())
              .build();
  }

  public CategoryCreateResponse toResponse(Category category) {
    if (category == null) {
      return null;
    }
    return CategoryCreateResponse.builder()
              .name(category.getName())
              .description(category.getDescription())
              .image(category.getImage())
              .build();
  }
}
