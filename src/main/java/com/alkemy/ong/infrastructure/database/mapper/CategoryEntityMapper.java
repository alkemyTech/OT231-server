package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
        .softDelete(categoryEntity.getSoftDelete())
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

  public Page<Category> toPageDomain(List<CategoryEntity> categoryEntities,
                                     int page, int size, Long totalPages) {
    Page<Category> dtoPage = new PageImpl<>(
            toDomain(categoryEntities),
            PageRequest.of(page, size), totalPages);
    return dtoPage;
  }


  public CategoryEntity toEntity(Category category) {
    if (category == null) {
      return null;
    }
    return CategoryEntity.builder()
        .name(category.getName())
        .description(category.getDescription())
        .image(category.getImage())
        .softDelete(category.getSoftDelete())
        .build();
  }

}
