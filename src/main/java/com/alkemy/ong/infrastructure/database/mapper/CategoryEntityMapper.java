package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.config.spring.security.common.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryEntityMapper {

    @Autowired
    private JwtUtils jwtUtils;

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
