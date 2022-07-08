package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CategoryEntityMapper {

  public Category toDomain(CategoryEntity categoryEntity) {
    if (categoryEntity == null) {
      return null;
    }
    return Category.builder()
        .name(categoryEntity.getName())
        .description(categoryEntity.getDescription())
        .image(categoryEntity.getImage())
        .build();
  }

  public List<Category> toDomain(List<CategoryEntity> categoryEntities) {
    if (categoryEntities == null || categoryEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<Category> categories = new ArrayList<>(categoryEntities.size());
    for (CategoryEntity categoryEntity : categoryEntities) {
      categories.add(toDomain(categoryEntity));
    }
    return categories;
  }

  public CategoryEntity toEntity(Category category) {
    if (category == null) {
      return null;
    }
    return CategoryEntity.builder()
        .name(category.getName())
        .description(category.getDescription())
        .image(category.getImage())
        .build();
  }

}
